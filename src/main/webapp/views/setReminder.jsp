<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Set a Reminder - Remly</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background: #f0f4f8;
            color: #2d3748;

        }
        .navbar {
            background: #4a5568;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .navbar-brand {
            font-weight: 700;
            font-size: 1.5rem;
            color: #ffffff !important;
        }
        .navbar-nav .nav-link {
            color: #cbd5e0 !important;
            font-weight: 500;
        }
        .navbar-nav .nav-link:hover {
            color: #ffffff !important;
        }
        .card {
            background: #ffffff;
            border: none;
            border-radius: 12px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .form-control {
            background: #edf2f7;
            border: none;
            border-radius: 8px;
            color: #2d3748;
        }
        .form-control:focus {
            background: #e2e8f0;
            box-shadow: none;
        }
        .form-label {
            font-weight: 600;
            color: #4a5568;
        }
        .btn-primary {
            background: #4299e1;
            border: none;
            border-radius: 8px;
            padding: 10px 20px;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        .btn-primary:hover {
            background: #3182ce;
            transform: translateY(-2px);
        }
        footer {
            background: #4a5568;
            color: #ffffff;
            padding: 10px 0;
            margin-top: 30px;
            text-align: center;
        }
        .form-check-input:checked {
            background-color: #4299e1;
            border-color: #4299e1;
        }
        .form-check-input:focus {
            box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.25);
        }
        .error-message {
            color: #e53e3e;
            font-size: 0.875rem;
            margin-top: 0.25rem;
            display: none;
        }
        .is-invalid {
            border: 1px solid #e53e3e !important;
        }
    </style>
</head>
<body>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="#">Remly</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Your Reminders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Form Container -->
<div class="container mt-5">
    <div class="card shadow-lg p-4">
        <h2 class="text-center mb-4" style="color: #2d3748;">Set a Reminder</h2>
        <form id="reminderForm" action="setReminder" method="post" onsubmit="return validateForm(event)">
            <!-- Title -->
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Enter reminder title" required>
                <div class="error-message" id="titleError">Title is required.</div>
            </div>
            <!-- Message -->
            <div class="mb-3">
                <label for="message" class="form-label">Message</label>
                <textarea class="form-control" id="message" name="message" rows="4" placeholder="Enter your reminder message" required></textarea>
                <div class="error-message" id="messageError">Message is required.</div>
            </div>
            <!-- Date and Time -->
            <div class="row mb-3">
                <div class="col-md-6">
                    <label for="reminder_date" class="form-label">Reminder Date</label>
                    <input type="date" class="form-control" id="reminder_date" name="reminderDate" required>
                    <div class="error-message" id="dateError">Date is required.</div>
                </div>
                <div class="col-md-6">
                    <label for="reminder_time" class="form-label">Reminder Time</label>
                    <input type="time" class="form-control" id="reminder_time" name="reminderTime" required>
                    <div class="error-message" id="timeError">Time is required.</div>
                </div>
            </div>
            <!-- Timezone -->
            <div class="mb-3">
                <label for="timezone" class="form-label">Timezone</label>
                <select class="form-control" id="timezone" name="timezone" required>
                    <option value="IST">Indian Standard Time (IST)</option>
                    <option value="UTC">Coordinated Universal Time (UTC)</option>
                    <option value="EST">Eastern Standard Time (EST)</option>
                    <option value="PST">Pacific Standard Time (PST)</option>
                </select>
                <div class="error-message" id="timezoneError">Timezone is required.</div>
            </div>
            <!-- Repeat Daily -->
            <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" id="is_daily" name="isDaily" onclick="toggleDateInput()">
                <label class="form-check-label" for="is_daily">Repeat Daily</label>
            </div>
            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary w-100">Set Reminder</button>
        </form>
    </div>
</div>

<!-- Footer -->
<footer style="">
    &copy; 2025 Remly. All rights reserved.
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script>
    // Function to toggle date input
    function toggleDateInput() {
        const dateInput = document.getElementById('reminder_date');
        if (document.getElementById('is_daily').checked) {
            dateInput.disabled = true;
            dateInput.value = '';
            document.getElementById('dateError').style.display = 'none';
            document.getElementById('reminder_date').classList.remove('is-invalid');
        } else {
            dateInput.disabled = false;
        }
    }

</script>
<script src="/validateForm.js"></script>
</body>
</html>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Set a Reminder</title>--%>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">--%>
<%--    <style>--%>
<%--        body {--%>
<%--            background-color: #2563EB;--%>
<%--            color: white;--%>
<%--        }--%>
<%--        .navbar {--%>
<%--            background-color: #1E40AF;--%>
<%--        }--%>
<%--        .card {--%>
<%--            background-color: white;--%>
<%--            color: black;--%>
<%--            border-radius: 10px;--%>
<%--        }--%>
<%--        .btn-primary {--%>
<%--            background-color: #2563EB;--%>
<%--            border-color: #2563EB;--%>
<%--        }--%>
<%--        .btn-primary:hover {--%>
<%--            background-color: #1E40AF;--%>
<%--            border-color: #1E40AF;--%>
<%--        }--%>
<%--        footer {--%>
<%--            background-color: #1E40AF;--%>
<%--            padding: 10px;--%>
<%--            text-align: center;--%>
<%--            margin-top: 20px;--%>
<%--        }--%>
<%--    </style>--%>
<%--    <script>--%>
<%--        function toggleDateInput() {--%>
<%--            const dateInput = document.getElementById('reminder_date');--%>
<%--            const dailyCheckbox = document.getElementById('is_daily');--%>
<%--            dateInput.disabled = dailyCheckbox.checked;--%>
<%--        }--%>
<%--    </script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<nav class="navbar navbar-expand-lg navbar-dark">--%>
<%--    <div class="container">--%>
<%--        <a class="navbar-brand" href="#">Remly</a>--%>
<%--        <div class="collapse navbar-collapse">--%>
<%--            <ul class="navbar-nav ms-auto">--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Your Reminders</a>--%>
<%--                </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Logout</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<div class="container mt-5">--%>
<%--    <div class="card shadow-lg p-4">--%>
<%--        <h2 class="text-center mb-4">Set a Reminder</h2>--%>
<%--        <form id="reminderForm" action="setReminder" method="post" onsubmit="return validateForm(event)">--%>
<%--            <div class="mb-3">--%>
<%--                <label for="title" class="form-label">Title</label>--%>
<%--                <input type="text" class="form-control" id="title" name="title" required>--%>
<%--            </div>--%>
<%--            <div class="mb-3">--%>
<%--                <label for="message" class="form-label">Message</label>--%>
<%--                <textarea class="form-control" id="message" name="message" rows="4" required></textarea>--%>
<%--            </div>--%>
<%--            <div class="mb-3">--%>
<%--                <label for="reminder_date" class="form-label">Reminder Date</label>--%>
<%--                <input type="date" class="form-control" id="reminder_date" name="reminderDate" required>--%>
<%--            </div>--%>
<%--            <div class="mb-3">--%>
<%--                <label for="reminder_time" class="form-label">Reminder Time</label>--%>
<%--                <input type="time" class="form-control" id="reminder_time" name="reminderTime" required>--%>
<%--            </div>--%>
<%--            <div class="mb-3">--%>
<%--                <label for="timezone" class="form-label">Timezone</label>--%>
<%--                <select class="form-control" id="timezone" name="timezone" required>--%>
<%--                    <option value="IST">Indian Standard Time (IST)</option>--%>
<%--                    <option value="UTC">Coordinated Universal Time (UTC)</option>--%>
<%--                    <option value="EST">Eastern Standard Time (EST)</option>--%>
<%--                    <option value="PST">Pacific Standard Time (PST)</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div class="form-check mb-3">--%>
<%--                <input class="form-check-input" type="checkbox" id="is_daily" name="is_daily" onclick="toggleDateInput()">--%>
<%--                <label class="form-check-label" for="is_daily">Repeat Daily</label>--%>
<%--            </div>--%>
<%--            <button type="submit" class="btn btn-primary w-100">Set Reminder</button>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<footer class="text-white">--%>
<%--    &copy; 2025 Remly. All rights reserved.--%>
<%--</footer>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--<script src="/validateForm.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
