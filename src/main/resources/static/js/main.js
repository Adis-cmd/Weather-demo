let notificationTimeout;

function showNotification(type, title, message) {
    const notification = document.getElementById('notification');
    const icon = document.getElementById('notificationIcon');
    const titleEl = document.getElementById('notificationTitle');
    const messageEl = document.getElementById('notificationMessage');

    if (type === 'error') {
        icon.textContent = '❌';
    } else if (type === 'success') {
        icon.textContent = '✅';
    } else if (type === 'info') {
        icon.textContent = '📍';
    }

    titleEl.textContent = title;
    messageEl.textContent = message;

    notification.className = 'notification show ' + type;

    if (notificationTimeout) {
        clearTimeout(notificationTimeout);
    }

    notificationTimeout = setTimeout(() => {
        closeNotification();
    }, 5000);
}

function closeNotification() {
    const notification = document.getElementById('notification');
    notification.classList.remove('show');
    if (notificationTimeout) {
        clearTimeout(notificationTimeout);
    }
}

function showLoading() {
    document.getElementById('loadingOverlay').classList.add('show');
}

function hideLoading() {
    document.getElementById('loadingOverlay').classList.remove('show');
}

function detectLocation() {
    if (!navigator.geolocation) {
        showNotification('error', 'Ошибка', 'Ваш браузер не поддерживает геолокацию');
        return;
    }

    showLoading();
    showNotification('info', 'Определение местоположения', 'Получаем доступ к вашей геолокации...');

    const options = {
        enableHighAccuracy: true,
        timeout: 15000,
        maximumAge: 0
    };

    navigator.geolocation.getCurrentPosition(
        function(position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;

            console.log('Координаты получены:', latitude, longitude);
            showNotification('info', 'Геолокация получена', 'Определяем название города...');

            fetch('https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=' + latitude + '&longitude=' + longitude + '&localityLanguage=ru')
                .then(response => {
                    console.log('Response status:', response.status);
                    if (!response.ok) {
                        throw new Error('Ошибка сети при геокодировании');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('Данные геокодирования:', data);
                    hideLoading();

                    const city = data.city ||
                        data.locality ||
                        data.principalSubdivision ||
                        data.countryName;

                    if (city) {
                        showNotification('success', 'Город найден!', 'Загружаем погоду для ' + city + '...');
                        setTimeout(() => {
                            window.location.href = '/?city=' + encodeURIComponent(city);
                        }, 1000);
                    } else {
                        console.error('Город не найден в данных:', data);
                        showNotification('error', 'Город не найден', 'Не удалось определить город. Введите название вручную.');
                    }
                })
                .catch(error => {
                    hideLoading();
                    console.error('Geocoding error:', error);
                    showNotification('error', 'Ошибка геокодирования', 'Не удалось определить город. Попробуйте позже или введите вручную.');
                });
        },
        function(error) {
            hideLoading();
            let errorTitle = 'Ошибка геолокации';
            let errorMessage = '';

            console.error('Geolocation error:', error);

            switch(error.code) {
                case error.PERMISSION_DENIED:
                    errorMessage = 'Доступ к геолокации запрещен. Разрешите доступ в настройках браузера.';
                    break;
                case error.POSITION_UNAVAILABLE:
                    errorMessage = 'Информация о местоположении недоступна. Проверьте GPS и интернет.';
                    break;
                case error.TIMEOUT:
                    errorMessage = 'Превышено время ожидания. Попробуйте еще раз.';
                    break;
                default:
                    errorMessage = 'Произошла неизвестная ошибка при определении местоположения.';
            }

            showNotification('error', errorTitle, errorMessage);
        },
        options
    );
}