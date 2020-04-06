# Microservicio que implementa un Saga con Camunda y Spring Retry
### Basado en :

https://github.com/camunda/camunda-bpm-examples/tree/master/spring-boot-starter/example-web

https://github.com/berndruecker/trip-booking-saga-java

https://docs.spring.io/spring-batch/docs/current/reference/html/retry.html

https://www.youtube.com/watch?v=ypX90aQScOQ&t=2s

https://www.adictosaltrabajo.com/2015/01/29/spring-retry/

https://www.baeldung.com/spring-retry

https://www.programcreek.com/java-api-examples/?api=org.springframework.retry.policy.TimeoutRetryPolicy

https://docs.camunda.org/manual/7.12/reference/rest/

https://blog.camunda.com/post/2015/08/start-and-complete-process-with-rest-api/

https://docs.camunda.org/manual/7.3/api-references/rest/#process-definition-start-process-instance

### Ejecucion:

Levantar Docker, luego levantar Jaeger: 

docker run -d -p 6831:6831/udp -p 16686:16686 jaegertracing/all-in-one:latest

En http://localhost:16686/ se veran las trazas.

Levantar mysql:

docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=camunda -d mysql:5.7.25

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

https://github.com/diegochavezcarro/camunda-async-caller

Dejar levantados los proyectos anteriores, levantar este también.

Levantar Kafka:

https://kafka.apache.org/quickstart

Ejecutar:

bin/zookeeper-server-start.sh config/zookeeper.properties

bin/kafka-server-start.sh config/server.properties

Ejecutar postman "Llamada asincronica al Camunda"

Ver las consolas, el Jaeger, etc

### Ejecucion en Kubernetes (camunda-retry, product-service, jaeger y mysql):

minikube start

En otra terminal dejar corriendo el tunnel para poder usar Services del tipo LoadBalancer:

minikube tunnel

Volver a la terminal original, ubicarse en la raiz del proyecto. Para no tener que subir las
imagenes a Docker Hub y sí subirlas al cache de imagenes del minikube:

minikube docker-env

eval $(minikube -p minikube docker-env)

Crear el jar:

mvn -B -DskipTests clean package

Crear la Imagen:

docker build -t diegochavezcarro/camunda-retry-app:1.0.0 .

Desplegar en kubernetes el proyecto product-service, ubicado en la raiz del mismo:

mvn -B -DskipTests clean package

docker build -t diegochavezcarro/product-app:1.0.0 .

kubectl create -f product-app-k8s-template.yaml

Volver al directorio de camunda-retry y desplegar lo demas en kubernetes:

kubectl create -f deploy-jaeger.yml

kubectl create -f deploy-mysql.yml

kubectl create -f camunda-retry-app-app-k8s-template.yaml

Se podrá ver el Dashboard si se deja corriendo en otra ventana:

minikube dashboard

Ver las IP y puertos de los servicios que se probaran de afuera:

kubectl get svc

Modificar los postman para poder acceder a la IP del servicio camunda-retry









