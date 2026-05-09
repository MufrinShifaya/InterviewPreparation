async function loadDashboard() {

    try {
        const [studentsRes, staffRes, attendanceRes] = await Promise.all([
            fetch("/students/all"),
            fetch("/staff/all"),
            fetch("/attendance/all")
        ]);

        const students   = await studentsRes.json();
        const staff      = await staffRes.json();
        const attendance = await attendanceRes.json();

        document.getElementById("totalStudents").innerText   = students.length;
        document.getElementById("totalTeachers").innerText   = staff.length;
        document.getElementById("totalAttendance").innerText = attendance.length;

    } catch (error) {
        console.error("Error loading counts:", error);
    }

    try {
        const response = await fetch("/attendance/defaulters");
        const data     = await response.json();
        const table    = document.getElementById("defaultersTable");

        if (data.length === 0) {
            table.innerHTML += `
                <tr>
                    <td colspan="4" style="text-align:center; padding:20px; color:#64748b;">
                        No defaulters. All students above 75%.
                    </td>
                </tr>`;
            return;
        }

        data.forEach(student => {
            table.innerHTML += `
                <tr>
                    <td>${student.studentId}</td>
                    <td>${student.studentName ?? "N/A"}</td>
                    <td>${student.department  ?? "N/A"}</td>
                    <td style="color:${student.percentage < 75 ? '#ef4444' : '#22c55e'}; font-weight:bold;">
                        ${student.percentage}%
                    </td>
                </tr>`;
        });

    } catch (error) {
        console.error("Error loading defaulters:", error);
    }
}

function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}

window.onload = () => { void loadDashboard(); };