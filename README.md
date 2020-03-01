# Microservicio que implementa un Saga con Camunda y Spring Retry
### Basado en :

https://github.com/camunda/camunda-bpm-examples/tree/master/spring-boot-starter/example-web

https://github.com/berndruecker/trip-booking-saga-java

https://docs.spring.io/spring-batch/docs/current/reference/html/retry.html

### Ejecucion:

Levantar Docker, luego levantar Jaeger: 

docker run -d -p 6831:6831/udp -p 16686:16686 jaegertracing/all-in-one:latest

En http://localhost:16686/ se veran las trazas.

Ver collections de Postman en carpeta postman.


Levantar una API que sera llamada:

https://github.com/dch-microdemo/product-service/

Ejecutar el postman localhost:8082/products/

1. Proyecto solo

mvn clean install

Levantar el jar con java -jar o con un IDE para Spring Boot

Ejecutar postman "procesos" para ver los procesos.

Tambien se pueden ver en el Cockpit:

http://localhost:8081/

Dar un usuario de alta y luego buscar la pagina del Cockpit.

Ejecutar el postman "empezar proceso". Para ver el otro proceso cambiar por "trip"

En el caso del trip2 se vera en el cockpit un proceso pendiente. Tmb se puede ver 

con el postman "ver tareas" (de todos los procesos) y con el "ver tareas del proceso"

Si se quiere completar esa tarea del usuario ejecutar postman "completar tarea" 

tomando el id obtenido en el punto anterior.

Jugar con Spring Retry apagando y levantando el microservicios de Products, el backoff configurado

del Spring Retry permite esto al tener 5 segundos por retry.

2. Proyecto llamado desde otro Microservicio:

https://github.com/diegochavezcarro/camunda-adapter

Ejecutar postman "camunda caller", ver Jaeger. Si se quiere jugar con Spring Retry sin Camunda ejecutar:

localhost:8083/retry

Y levantar y apagar el MS de Products.

3. Llamada Asincronica:

Dejar levantados los proyectos anteriores

Levantar Kafka:

https://kafka.apache.org/quickstart

Ejecutar:

bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties

Ejecutar postman "Llamada asincronica al Camunda"

Ver las consolas, el Jaeger, etc








