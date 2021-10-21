# spring-security-jwt-Docker

Сервис авторизации пользователей, сохранение и чтнение пользовательских сообщений


Создание образа и запуск контейнера docker


./mvnw clean package -DskipTests // приложение как файл JAR

cp target/spring-security-jwt.jar src/main/docker //копируем файл JAR в директорию src/main/docker

cd C:\download\spring-security-jwt\src\main\docker // меяем текущий каталог src\main\docker  запускаем docker compose

docker build -t spring-security-jwt.jar .   // создаем "image"  проекта

docker compose up -d //запускаем docker compose



Описание эндпойнтов:  

@RestController AuthController

@PostMapping("/register")   //http://localhost:8080/register
регистрация в БД нового пользователя request: RegistrationRequestDto { "login": "", "password": "" } response: "OK"

@PostMapping("/auth")    //http://localhost:8080/auth
получение jwt токена зарегестрированным пользователем request: AuthRequestDto { "login": "", "password": "" } response: AuthResponseDto { "{сгенерированный jwt токен}", }

@RestController MessageController

@PostMapping("/message")  http://localhost:8080/message
сохранение сообщения от авторизованного пользователя, либо получение истории сообщений request: MessageRequestDto { "login": "", "message": "" } response: List возвращает переданное сообщение, либо при сообщении вида "history x" возвращает х последних пользовательских сообщений

