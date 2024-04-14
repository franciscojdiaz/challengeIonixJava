# Ionix-Challenge-ApiRest-Java-Mysql

En este proyecto se utilizan las siguientes tecnologias:
Java 17, springboot framework 3.2.4, Mysql latest.

Composición del proyecto:
Tiene una sección, donde a través de api rest, se hacen las operaciones de consultas, creación y eliminación de objetos hacia la base de datos. Las apis rest de creación y eliminación de objetos están condicionadas a nivel de seguridad con las siguientes credenciales;
spring.security.user.name = ionix
spring.security.user.password = ionix123
Esta para su configuración en la sección de autorización en postman.

Cabe destacar que hay un controller particular donde se hace una consulta a un api externo, para este punto se utilizó spring web client.
Por otro lado, cuenta con una sección de test unitarios, para la sección de controllers y services.
Para facilitar la ejecución del proyecto se estará enviando la collection de la API para su ejecución en postman.
