const API_BASE_URL = "http://localhost:8080";

// --- 1. LOGIN LOGIC ---
async function login() {
    const phone = document.getElementById('phone').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch(`${API_BASE_URL}/user/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ phone, password })
        });

        const result = await response.text(); 
        
        if (result.includes("successful")) {
            localStorage.setItem('loggedUserPhone', phone);
            // Temporary: We use ID 1 for your testing session
            localStorage.setItem('currentUserId', "1"); 
            window.location.href = 'dashboard.html';
        } else {
            alert("Invalid Credentials: " + result);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Backend is not running!");
    }
}

// --- 2. USER REGISTRATION ---
async function register() {
    const user = {
        name: document.getElementById('regName').value,
        phone: document.getElementById('regPhone').value,
        address: document.getElementById('regAddress').value,
        password: document.getElementById('regPassword').value
    };

    try {
        const response = await fetch(`${API_BASE_URL}/user/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            alert("Registration Successful! Please login.");
            window.location.href = 'index.html';
        } else {
            alert("Error: Phone number might already be registered.");
        }
    } catch (err) {
        console.error("Registration Error:", err);
    }
}

// --- 3. DASHBOARD LOGIC (BILLS) ---
async function loadBills() {
    const tbody = document.querySelector('#billTable tbody');
    if (!tbody) return;

    const userId = localStorage.getItem('currentUserId') || "1";

    try {
        const response = await fetch(`${API_BASE_URL}/bill/user/${userId}`);
        const bills = await response.json();

        tbody.innerHTML = ''; 
        bills.forEach(bill => {
            const statusClass = bill.status === 'UNPAID' ? 'unpaid' : 'paid';
            const row = `<tr>
                <td>${bill.billId}</td>
                <td>${bill.units}</td>
                <td>₹${bill.amount}</td>
                <td class="${statusClass}">${bill.status}</td>
                <td>
                    ${bill.status === 'UNPAID' 
                        ? `<button style="width:auto" onclick="payBill(${bill.billId})">Pay</button>` 
                        : 'Done'}
                </td>
            </tr>`;
            tbody.innerHTML += row;
        });
    } catch (error) {
        console.error("Error loading bills:", error);
    }
}

async function payBill(billId) {
    const response = await fetch(`${API_BASE_URL}/bill/pay/${billId}`, {
        method: 'PUT'
    });
    if (response.ok) {
        alert("Bill Paid!");
        loadBills();
    }
}

// --- 4. ADMIN ACTIONS ---
async function addMeter() {
    const userId = document.getElementById('targetUserId').value;
    const meter = {
        meterNumber: document.getElementById('meterNum').value,
        meterType: document.getElementById('mType').value
    };

    const response = await fetch(`${API_BASE_URL}/meter/add/${userId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(meter)
    });
    
    if(response.ok) {
        alert("Meter Assigned Successfully!");
    } else {
        alert("Failed to assign meter. Check User ID.");
    }
}

async function generateBillAdmin() {
    const mId = document.getElementById('targetMeterId').value;
    const units = document.getElementById('unitsConsumed').value;

    // This uses the GET mapping we updated in your BillController
    const response = await fetch(`${API_BASE_URL}/bill/generate/${mId}/${units}`);
    if (response.ok) {
        alert("New Bill Generated!");
    } else {
        alert("Error generating bill.");
    }
}