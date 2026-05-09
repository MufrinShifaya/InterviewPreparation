async function loadAttendance() {

    const studentId   = localStorage.getItem("studentId");
    const studentName = localStorage.getItem("studentName");
    const department  = localStorage.getItem("department");

    if (!studentId) {
        alert("Session expired. Please login again.");
        window.location.href = "index.html";
        return;
    }

    document.getElementById("studentId").innerText   = studentId;
    document.getElementById("studentName").innerText = studentName || "N/A";
    document.getElementById("department").innerText  = department  || "N/A";

    try {
        const res = await fetch("/attendance/summary/" + studentId);

        if (!res.ok) {
            document.getElementById("percentage").innerText = "API Error " + res.status;
            return;
        }

        const data = await res.json();
        console.log("Attendance:", data);

        document.getElementById("totalPeriods").innerText = data.totalPeriods ?? 0;
        document.getElementById("present").innerText      = data.present      ?? 0;
        document.getElementById("absent").innerText       = data.absent       ?? 0;
        document.getElementById("percentage").innerText   = (data.percentage  ?? 0) + "%";

    } catch (error) {
        console.error("Error:", error);
        document.getElementById("percentage").innerText = "Error loading data";
    }
}

function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}

window.onload = () => { void loadAttendance(); };