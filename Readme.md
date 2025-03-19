# Daily Calorie Tracker
REST API сервис для отслеживания ежедневного потребления калорий и управления питанием.

## Описание
Приложение позволяет пользователям:
- Регистрироваться и управлять профилем
- Добавлять и отслеживать приемы пищи
- Управлять списком блюд
- Получать отчеты о потреблении калорий
- Автоматически рассчитывать дневную норму калорий

## Технические требования
- Java 17 или выше
- PostgreSQL
- Maven для сборки
- Spring Boot 3.x

## API Эндпоинты
### Пользователи
#### Создание пользователя
```bash
POST /users
Content-Type: application/json
{
  "name": "Иван Иванов",
  "email": "ivan@example.com",
  "age": 30,
  "weight": 80,
  "height": 180,
  "target": "LOSE_WEIGHT"
}
```

#### Обновление пользователя
```bash
PATCH /users/{userId}
Content-Type: application/json
{
  "weight": 85
}
```

#### Удаление пользователя
```bash
DELETE /users/{userId}
```

### Блюда
#### Добавление блюда
```bash
POST /products
Content-Type: application/json
{
  "name": "Омлет",
  "calories": 200,
  "proteins": 10,
  "fats": 15,
  "carbs": 5
}
```

#### Обновление блюда
```bash
PATCH /products/{productId}
Content-Type: application/json
{
  "calories": 220
}
```

#### Удаление блюда
```bash
DELETE /products/{productId}
```

### Приемы пищи
#### Добавление приема пищи
```bash
POST /meals
Content-Type: application/json
{
  "userId": 1,
  "dateTime": "2023-10-05T12:00:00",
  "products": [1, 2, 3]
}
```

### Отчеты
#### Дневной отчет
```bash
GET /meals/report/{userId}?date=2023-10-05
Content-Type: application/json
{
    "userName": "Иван Иванов",
    "email": "ivan@example.com",
    "userCalories": 1864.529,
    "dailyNutritionSummary": {
        "caloriesPerDay": 0.0,
        "products": [
            "Вода",
            "Вода",
            "Вода"
        ],
        "squirrels": 0.0,
        "fats": 0.0,
        "carbohydrates": 0.0
    },
    "message": "Пользователь Иван Иванов не уложился в дневную норму"
}
```

#### История питания
```bash
GET /meals/{userId}
Content-Type: application/json 
{
    "userName": "Иван Иванов",
    "email": "ivan@example.com",
    "userCalories": 1864.529,
    "history": {
        "2023-10-05": {
            "caloriesPerDay": 0.0,
            "products": [
                "Вода",
                "Вода",
                "Вода"
            ],
            "squirrels": 0.0,
            "fats": 0.0,
            "carbohydrates": 0.0
        }
    }
}
```

## Структура проекта
```markdown
src
├── main
│   ├── java
│   │   └── com.example.calorie.tracker
│   │       ├── controller
│   │       ├── dto
|   |       ├── enums
|   |       ├── exception
|   |       ├── mapper
│   │       ├── model
│   │       ├── repository
│   │       ├── service
│   │       └── TestCaseApplication.java
│   └── resources
│       └── application.properties
└── test
    └── java
        └── com.example.calorie.tracker
```

## Архитектура приложения
Архитектура построена по принципу слоев и включает следующие компоненты:

1. слой (Controller):
    - Обработка HTTP-запросов
    - Преобразование DTO в сущности

2. слой (Service):
    - Бизнес-логика приложения
    - Расчет калорий по формуле Харриса-Бенедикта
    - Логика работы с приемами пищи

3.  слой (Repository):
    - Управление данными через Spring Data JPA
    - CRUD операции над сущностями

4. Слой DTO:
    - Перенос данных между слоями
    - Изоляция внутренней структуры от API
    - Валидация входных данных
   
5. слой (Mapper):
    - Преобразование DTO в сущности
    - Преобразование сущностей в DTO
    

## Особенности реализации
- Автоматический расчет дневной нормы калорий
- Поддержка различных целей питания (похудение, поддержание веса, набор массы)
- Полная история приемов пищи
- Детальные отчеты о потреблении питательных веществ