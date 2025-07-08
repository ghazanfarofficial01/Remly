function validateForm(event) {
    const dateInput = document.getElementById('reminder_date');
    const timeInput = document.getElementById('reminder_time');
    const dailyCheckbox = document.getElementById('is_daily');

    const currentDate = new Date();
    const selectedDate = new Date(dateInput.value);
    const selectedTime = timeInput.value;
    console.log(selectedTime);
    if (!dailyCheckbox.checked && selectedDate.setHours(0, 0, 0, 0) < currentDate.setHours(0, 0, 0, 0)) {
        alert('Reminder date must be today or a future date.');
        event.preventDefault();
        return false;
    }

    // Validate Time
    if (!timeInput.value) {
        alert('Please select a reminder time.');
        event.preventDefault();
        return false;
    }

    // Check if the selected time is in the past for today's date
    if (selectedDate.setHours(0, 0, 0, 0) == currentDate.setHours(0, 0, 0, 0)) {
        const currentHours = currentDate.getHours();
        const currentMinutes = currentDate.getMinutes();
        const [selectedHours, selectedMinutes] = selectedTime.split(':').map(Number);
        console.log(currentMinutes, currentHours);
        if (selectedHours < currentHours || (selectedHours === currentHours && selectedMinutes <= currentMinutes)) {
            alert('Reminder time must be in the future.');
            console.log("under if");
            event.preventDefault();
            return false;
        }
    }


    if (dailyCheckbox.checked) {
        dateInput.value = '';
        dateInput.disabled = true;
    } else {
        dateInput.disabled = false;
    }

    return true;
}
