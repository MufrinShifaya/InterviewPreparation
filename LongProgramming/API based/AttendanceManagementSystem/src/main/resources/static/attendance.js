async function loadAttendanceRecords() {

    const studentId = localStorage.getItem("studentId");
    const role      = localStorage.getItem("role");

    if (!studentId && role === "student") {
        alert("Session expired. Please login again.");
        window.location.href = "index.html";
        return;
    }

    const tbody = document.getElementById("attendanceTable");

    try {
        const res = await fetch("/attendance/student/" + studentId);

        if (!res.ok) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="5" class="empty-msg">
                        Error loading records: ${res.status}
                    </td>
                </tr>`;
            return;
        }

        const records = await res.json();
        console.log("Records:", records);

        if (records.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="5" class="empty-msg">
                        No attendance records found.
                    </td>
                </tr>`;
            return;
        }

        tbody.innerHTML = "";

        records.forEach((record, index) => {
            const isPresent = record.status === "Present";
            tbody.innerHTML += `
                <tr>
                    <td>${index + 1}</td>
                    <td>${record.subject  ?? "N/A"}</td>
                    <td>${record.date     ?? "N/A"}</td>
                    <td>${record.period   ?? "N/A"}</td>
                    <td>
                        <span class="badge ${isPresent ? 'present' : 'absent'}">
                            ${record.status}
                        </span>
                    </td>
                </tr>`;
        });

    } catch (error) {
        console.error("Error:", error);
        tbody.innerHTML = `
            <tr>
                <td colspan="5" class="empty-msg">
                    Cannot connect to backend.
                </td>
            </tr>`;
    }
}

function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}

window.onload = () => { void loadAttendanceRecords(); };