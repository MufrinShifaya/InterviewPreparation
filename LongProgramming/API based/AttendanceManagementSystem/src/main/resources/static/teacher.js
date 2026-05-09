async function markAttendance() {

    const studentId = document.getElementById("studentId").value;
    const subject   = document.getElementById("subject").value;
    const date      = document.getElementById("date").value;
    const period    = document.getElementById("period").value;
    const status    = document.getElementById("status").value;

    if (!studentId || !subject || !date || !period) {
        showMessage("Please fill all fields", "error");
        return;
    }

    try {
        const response = await fetch("/attendance/mark", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ studentId, subject, date, period, status })
        });

        const data = await response.text();

        if (response.ok) {
            showMessage("✅ Attendance marked successfully!", "success");
            document.getElementById("studentId").value = "";
            document.getElementById("subject").value   = "";
            document.getElementById("period").value    = "";
        } else {
            showMessage("❌ Error: " + data, "error");
        }

    } catch (error) {
        showMessage("❌ Cannot connect to backend.", "error");
        console.error(error);
    }
}

function showMessage(msg, type) {
    const box = document.getElementById("message");
    box.innerText       = msg;
    box.style.display   = "block";
    box.style.background = type === "success" ? "#dcfce7" : "#fee2e2";
    box.style.color      = type === "success" ? "#166534" : "#991b1b";
    box.style.border     = type === "success" ? "1px solid #86efac" : "1px solid #fca5a5";
    setTimeout(() => { box.style.display = "none"; }, 4000);
}

function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}