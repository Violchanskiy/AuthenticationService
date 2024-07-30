# Проект Сервиса Аутентификации


Проект сервис аутентификации представляет собой приложение на Spring Boot, предназначенное для обработки аутентификации и регистрации пользователей с использованием JWT (JSON Web Tokens). Он предоставляет RESTful API эндпоинты для регистрации, входа и идентификации пользователей. Проект интегрирует Spring Security для безопасной аутентификации и авторизации, а также Swagger для документирования API.

## Используемые Технологии

- **Spring Boot**: Фреймворк для создания автономных, готовых к производству приложений на основе Spring.
- **Spring Security**: Фреймворк для обеспечения безопасности приложений на основе Spring.
- **JWT (JSON Web Tokens)**: Открытый стандарт для создания токенов доступа, которые подтверждают некоторые утверждения.
- **Swagger**: Инструмент для проектирования, создания, документирования и использования RESTful веб-сервисов.
- **BCrypt**: Функция хеширования паролей.

## API Эндпоинты

### Контроллер Аутентификации

- **POST /auth/signUp**
    - **Описание**: Регистрация нового пользователя.
    - **Параметры**:
        - `username` (String): Имя пользователя нового пользователя.
        - `password` (String): Пароль нового пользователя.
    - **Ответы**:
        - `200 OK`: Пользователь успешно зарегистрирован.
        - `400 Bad Request`: Пользователь с таким именем уже существует.

- **POST /auth/signIn**
    - **Описание**: Аутентификация пользователя и генерация JWT.
    - **Параметры**:
        - `username` (String): Имя пользователя пользователя.
        - `password` (String): Пароль пользователя.
    - **Ответы**:
        - `200 OK`: Возвращает JWT токен.
        - `400 Bad Request`: Неверное имя пользователя или пароль.

### Контроллер Пользователя

- **GET /user/{username}**
    - **Описание**: Идентификация и приветствие пользователя.
    - **Переменная Пути**:
        - `username` (String): Имя пользователя пользователя.
    - **Ответы**:
        - `200 OK`: Возвращает приветственное сообщение.

## Конфигурация

### Конфигурация Безопасности

- **SecurityConfig**: Настраивает Spring Security для обеспечения безопасности приложения. Он устанавливает правила аутентификации и авторизации, а также интегрирует JWT для токена на основе токенов.

### Конфигурация Swagger

- **SwaggerConfig**: Настраивает Swagger для документирования API эндпоинтов. Он указывает базовый пакет для сканирования API контроллеров и включает Swagger UI.

## Сервисы

### Сервис Пользователя

- **UserService**: Интерфейс, определяющий методы для регистрации, аутентификации и идентификации пользователей.
- **UserServiceImpl**: Реализация интерфейса UserService, обрабатывающая операции с пользователями и интегрирующаяся с UserRepository.

### Сервис JWT

- **JwtService**: Сервис для генерации и валидации JWT токенов.
- **JwtFilter**: Фильтр для перехвата и обработки JWT токенов во входящих запросах.

## Модели

- **User**: Сущность, представляющая пользователя в системе, с полями для `id`, `username` и `password`.

## Репозитории

- **UserRepository**: JPA репозиторий для выполнения CRUD операций над сущностью `User`.

## Исключения

- **UserAlreadyExistsException**: Пользовательское исключение, выбрасываемое, когда пользователь пытается зарегистрироваться с уже существующим именем пользователя.

## Запуск Приложения
Для запуска приложения используйте Maven. Выполните следующие шаги:

1. Откройте терминал и перейдите в корневую директорию проекта.
2. Соберите проект с помощью Maven:
    ```sh
    mvn clean install
    ```
3. Запустите приложение с помощью Maven:
    ```sh
    mvn spring-boot:run
    ```
4. Приложение запустится на стандартном порту `8080`. Вы можете проверить его работу, перейдя по адресу `http://localhost:8080`.
