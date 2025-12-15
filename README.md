# Weather Application

Веб-приложение для просмотра текущей погоды по геолокации или поиску города.

## Возможности

- Определение погоды по геолокации
- Поиск погоды по названию города
- Запуск через Docker

## Быстрый старт

### 1. Получите API ключ

Зарегистрируйтесь на [WeatherAPI.com](https://www.weatherapi.com/) и получите бесплатный API ключ.

### 2. Настройте ключ

Создайте файл `.env`:

```env
WEATHER_API_KEY=ваш_ключ_здесь
```

Или укажите ключ напрямую в `application.yml`:

```yaml
weather:
  api:
    url: https://api.weatherapi.com/v1/current.json
    key: ваш_ключ_здесь
```

### 3. Запустите приложение

```bash
docker-compose up --build
```

Откройте [http://localhost:8099](http://localhost:8099)

## Остановка

```bash
docker-compose down
```

## Troubleshooting

- **Ошибка 401:** Проверьте правильность API ключа
- **Геолокация не работает:** Разрешите доступ в настройках браузера
