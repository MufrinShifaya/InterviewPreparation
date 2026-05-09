async function login() {

    const email    = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const role     = document.getElementById("role").value;

    if (!email || !password) {
        alert("Please enter email and password");
        return;
    }

    let url = "";
    if (role === "student")      url = "/students/login";
    else if (role === "teacher") url = "/staff/login";
    else                         url = "/admin/login";

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        // Read as text first
        const text = await response.text();
        console.log("Raw response:", text);

        if (!response.ok) {
            alert("Login failed. Check email and password.");
            return;
        }

        // Try to parse as JSON, if not just use text
        let data = {};
        try {
            data = JSON.parse(text);
        } catch (e) {
            // Backend returned plain text like "Login Successful"
            // We still need to get student details separately
            console.log("Plain text response:", text);
        }

        if (role === "student") {
            // If backend returns JSON with student details
            if (data.studentId) {
                localStorage.setItem("studentId",   data.studentId);
                localStorage.setItem("studentName", data.name);
                localStorage.setItem("department",  data.department);
            } else {
                // Backend only returns "Login Successful" text
                // Fetch student details separately using email
                const studentRes  = await fetch("/students/by-email?email=" + email);
                const studentData = await studentRes.json();
                localStorage.setItem("studentId",   studentData.studentId);
                localStorage.setItem("studentName", studentData.name);
                localStorage.setItem("department",  studentData.department);
            }
            localStorage.setItem("role", "student");
            window.location.href = "student.html";

        } else if (role === "teacher") {
            if (data.staffId) {
                localStorage.setItem("staffId",   data.staffId);
                localStorage.setItem("staffName", data.name);
            }
            localStorage.setItem("role", "teacher");
            window.location.href = "teacher.html";

        } else {
            localStorage.setItem("role", "admin");
            window.location.href = "admin.html";
        }

    } catch (error) {
        console.error("Login error:", error);
        alert("Login failed: " + error.message);
    }
}