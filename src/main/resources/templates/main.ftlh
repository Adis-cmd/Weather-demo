<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/main.css">
    <title>Погода -
        <#if weather?? && weather.location?? && weather.location.name?has_content>
            ${weather.location.name}
        <#else>
            -
        </#if>
    </title>
</head>
<body>
<div id="notification" class="notification">
    <div class="notification-content">
        <span class="notification-icon" id="notificationIcon">ℹ️</span>
        <div class="notification-text">
            <div class="notification-title" id="notificationTitle">Уведомление</div>
            <div class="notification-message" id="notificationMessage"></div>
        </div>
        <button class="notification-close" onclick="closeNotification()">✕</button>
    </div>
</div>

<div id="loadingOverlay" class="loading-overlay">
    <div class="loading-spinner"></div>
</div>

<div class="container">
    <div class="header">
        <h1>Прогноз погоды</h1>
        <form class="location-form" action="/" method="get">
            <div class="search-box">
                <input
                        type="text"
                        name="city"
                        placeholder="Введите название города..."
                        value="${city!''}"
                        required
                >
                <button type="submit">Найти</button>
            </div>
        </form>
        <button class="auto-detect-btn" onclick="detectLocation()">
            <span>Определить автоматически</span>
        </button>
    </div>

    <#if weather??>
        <div class="weather-content">
            <div class="location-info">
                <div class="city-name">${weather.location.name}</div>
                <div class="country-name">${weather.location.country}</div>
            </div>

            <div class="weather-main">
                <div>
                    <span class="temperature">${weather.current.temp_c?string("0.0")}</span>
                    <span class="temp-unit">°C</span>
                </div>
                <img src="https:${weather.current.condition.icon}" alt="Weather icon" class="weather-icon">
            </div>

            <div class="condition-text">
                ${weather.current.condition.text}
            </div>

            <div class="weather-details">
                <div class="detail-item">
                    <span class="detail-label">Температура</span>
                    <span class="detail-value">${weather.current.temp_c?string("0.0")}°C</span>
                </div>
                <div class="detail-item">
                    <span class="detail-label">Состояние</span>
                    <span class="detail-value">${weather.current.condition.text}</span>
                </div>
            </div>
        </div>

        <div class="footer">
            Обновлено: ${.now?string("dd.MM.yyyy HH:mm")}
        </div>
    <#else>
        <div class="weather-content">
            <div class="empty-state">
                <div class="empty-state-icon">
                    <#if error??>
                        ⚠️
                    <#else>
                        🌡️
                    </#if>
                </div>
                <div class="empty-state-title">
                    <#if error??>
                        ${error}
                    <#else>
                        Введите город для просмотра погоды
                    </#if>
                </div>
                <div class="empty-state-message">
                    <#if error??>
                        Проверьте правильность написания или попробуйте другой город
                    <#else>
                        Используйте поиск выше или автоопределение местоположения
                    </#if>
                </div>
            </div>
        </div>
    </#if>
</div>

<script>
    const urlParams = new URLSearchParams(window.location.search);
    const city = urlParams.get('city');

    <#if error?? && city??>
    showNotification('error', 'Город не найден', 'Город "${city}" не найден. Проверьте правильность написания.');
    </#if>
</script>
<script src="/js/main.js"></script>
</body>
</html>