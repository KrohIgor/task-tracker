"# task-tracker" 

Для запуска REST API нужно:
1. Создать схему в базе данных MySQL и прописать
настройки схемы узера и пароля в файле application.properties

Пример: 
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/task?serverTimezone=Europe/Kiev
spring.datasource.username=root
spring.datasource.password=****
2. В бузу данных добавятся возможные статусы тасков и
один юзер(email = bob@ukr.net, password = 1234)

3. Нужно отправить POST запрос на 
http://localhost:8080/auth/login и в теле запроса
передать JSON {"userName":"bob@ukr.net","password":"1234"}
в ответ получите JSON. Пример:
{
  "userName": "bob@ukr.net",
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JAdWtyLm5ldCIsImlhdCI6MTU5MzI0NjU2MCwiZXhwIjoxNTkzMjUwMTYwfQ.LlM8q2ASJhkI8pEWIItINudm8PCNTMyNrI_KwqENFwk"
}
4.Все последующие запросы нужно отправлять с Header:
Authentication=Bearer_token
Пример:
Authentication=Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JAdWtyLm5ldCIsImlhdCI6MTU5MzI0NjU2MCwiZXhwIjoxNTkzMjUwMTYwfQ.LlM8q2ASJhkI8pEWIItINudm8PCNTMyNrI_KwqENFwk
5. Для добавления тестовых данных в базу данных отправте POST запросы:
http://localhost:8080/inject/user - добавление 50 узеров
http://localhost:8080/inject/task - добавление 50 тасков

При выполнении задания не использовал docker, с этой технологией только
буду начинать знакомиться.