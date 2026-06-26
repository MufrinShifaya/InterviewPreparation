const API_BASE = 'http://localhost:8080';
let token = localStorage.getItem('token') || '';
let currentScreen = 'auth';
let authMode = 'login';
let selectedLocation = '';
let selectedCompany = '';
let selectedRole = '';

// Check if user already logged in
window.addEventListener('load', () => {
    if (token) {
        showScreen('locations');
        fetchLocations();
    }
});

// ===== AUTH =====
function switchAuthMode(mode) {
    authMode = mode;
    const nameInput = document.getElementById('nameInput');
    const authBtn = document.getElementById('authBtn');
    const tabs = document.querySelectorAll('.tab-btn');

    tabs.forEach(t => t.classList.remove('active'));
    event.target.classList.add('active');

    if (mode === 'register') {
        nameInput.style.display = 'block';
        authBtn.textContent = 'Register';
    } else {
        nameInput.style.display = 'none';
        authBtn.textContent = 'Login';
    }
}

async function handleAuth() {
    const email = document.getElementById('emailInput').value;
    const password = document.getElementById('passwordInput').value;
    const name = document.getElementById('nameInput').value;

    if (!email || !password) {
        showError('authError', 'Please fill all fields');
        return;
    }

    if (authMode === 'register' && !name) {
        showError('authError', 'Name is required');
        return;
    }

    try {
        const endpoint = authMode === 'login' ? '/auth/login' : '/auth/register';
        const body = authMode === 'login'
            ? { email, password }
            : { name, email, password };

        const response = await fetch(`${API_BASE}${endpoint}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });

        const data = await response.text();

        if (response.ok) {
            token = data.includes('token') ? JSON.parse(data).token : data;
            localStorage.setItem('token', token);
            showScreen('locations');
            fetchLocations();
        } else {
            showError('authError', data || 'Auth failed');
        }
    } catch (err) {
        showError('authError', 'Error: ' + err.message);
    }
}

// ===== LOCATIONS =====
async function fetchLocations() {
    try {
        const response = await fetch(`${API_BASE}/locations`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        const locations = await response.json();

        const html = locations.map(loc =>
            `<button class="location-btn" onclick="selectLocation('${loc.cityName}')">${loc.cityName}</button>`
        ).join('');

        document.getElementById('locationsList').innerHTML = html;
    } catch (err) {
        console.error('Error fetching locations:', err);
        showError('locationsError', 'Failed to load locations: ' + err.message);
    }
}

function selectLocation(location) {
    selectedLocation = location;
    fetchCompanies();
}

// ===== COMPANIES =====
async function fetchCompanies() {
    document.getElementById('companiesTitle').textContent = `🏢 IT Companies in ${selectedLocation}`;
    document.getElementById('companiesContent').textContent = 'Loading...';
    showScreen('companies');

    try {
        const response = await fetch(`${API_BASE}/ai/companies`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ location: selectedLocation })
        });

        const data = await response.text();
        document.getElementById('companiesContent').textContent = data;
    } catch (err) {
        document.getElementById('companiesContent').textContent = 'Error: ' + err.message;
    }
}

function selectCompany() {
    selectedCompany = document.getElementById('companyInput').value;
    if (!selectedCompany) {
        showError('companiesError', 'Please enter a company name');
        return;
    }
    fetchRoles();
}

// ===== ROLES =====
async function fetchRoles() {
    document.getElementById('rolesTitle').textContent = `💼 Roles at ${selectedCompany}`;
    document.getElementById('rolesContent').textContent = 'Loading...';
    showScreen('roles');

    try {
        const response = await fetch(`${API_BASE}/ai/roles`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ company: selectedCompany })
        });

        const data = await response.text();
        document.getElementById('rolesContent').textContent = data;
    } catch (err) {
        document.getElementById('rolesContent').textContent = 'Error: ' + err.message;
    }
}

function selectRole() {
    selectedRole = document.getElementById('roleInput').value;
    if (!selectedRole) {
        showError('rolesError', 'Please enter a role name');
        return;
    }
    fetchDetails('details');
}

// ===== DETAILS =====
async function fetchDetails(type) {
    document.getElementById('detailsTitle').textContent = `📋 ${selectedRole} at ${selectedCompany}`;
    document.getElementById('detailsContent').textContent = 'Loading...';
    showScreen('details');

    const endpoints = {
        details: '/ai/details',
        rounds: '/ai/interview-rounds',
        questions: '/ai/interview-questions',
        roadmap: '/ai/roadmap',
        resume: '/ai/resume',
        projects: '/ai/projects'
    };

    try {
        const response = await fetch(`${API_BASE}${endpoints[type]}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({ company: selectedCompany, role: selectedRole })
        });

        const data = await response.text();
        document.getElementById('detailsContent').textContent = data;
    } catch (err) {
        document.getElementById('detailsContent').textContent = 'Error: ' + err.message;
    }
}

// ===== NAVIGATION =====
function showScreen(screen) {
    document.querySelectorAll('.card').forEach(c => c.classList.add('hidden'));
    document.getElementById(screen + 'Screen').classList.remove('hidden');
    currentScreen = screen;

    // Clear error messages
    const errorElements = document.querySelectorAll('[id$="Error"]');
    errorElements.forEach(el => el.innerHTML = '');
}

function goBack(screen) {
    showScreen(screen);
}

function logout() {
    localStorage.removeItem('token');
    token = '';
    showScreen('auth');
    document.getElementById('emailInput').value = '';
    document.getElementById('passwordInput').value = '';
    document.getElementById('nameInput').value = '';
    document.getElementById('authError').innerHTML = '';
}

function showError(elementId, message) {
    const errorElement = document.getElementById(elementId);
    if (errorElement) {
        errorElement.innerHTML = `<div class="error">${message}</div>`;
    }
}
