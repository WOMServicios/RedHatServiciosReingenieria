WOM - REST Proxy Apache Camel - Spring Boot
===========================================

Permite generar proyectos del tipo API Rest los cuales serán expuestos basados en una situación de proxy, internamente si el cuerpo cambiara entre el servicio consumido
y producido, se deberá generar un proceso de transformación eventualmente y todas las actividades adicionales que sean necesarias.

### Consideraciones:

* El arquetipo no generará rutinas de validación o transformación intermedias, éstas deberán ser ajustadas por el programador al momento de generar el servicio.
* El arquetipo siempre responderá a una situación de proxy.
* El arquetipo generará un servicio que siempre consumirá un servicio rest con método get.
* El arquetipo generará un servicio basado en el modelo "pass through".
* El contrato del servicio deberá ser adecuado dependiendo de las necesidades de interpretación, tanto de transformación como de invocación hacia y desde servicio de destino.

### Requerimientos:
* Spring Boot
* Apache Maven 
* Java SE 8

Construcción
------------

Para construir e instalar el arquetipo en el repositorio maven se deberá ejecutar la siguiente línea dentro del proyecto

    mvn clean install

Nota: Con la anterior línea el arquetipo quedará instalado en el repositorio local el cual deberá ser versionado sobre Repositorio Maven correspondiente.

Ejecución desde línea de comando
--------------------------------

Después de instalado el arquetipo en el repositorio local, se deberá ejecutar la siguiente línea 
para la generación del proyecto.

	mvn archetype:generate -DarchetypeGroupId=cl.wom.middleware.arquetype \ 
	-DarchetypeArtifactId=middleware-rest-proxy-archetype \
	-DarchetypeVersion=1.0.0 -DgroupId=cl.wom.middleware.proxy \
	-DartifactId=middleware-rest-proxy-base -Dversion=1.0.0 \ 
	-DresourceService=saldo/cliente/1 -DportProxy=8888 \ 
	-DserviceTo=http://jsonplaceholder.typicode.com/posts/1 \ 
	-DinteractiveMode=false -DbasicAuth=false

	
* -DresourceService: atributo que define el nombre de la ruta camel, que servirá para diferenciarla de las demás rutas existentes.
* -DportProxy: puerto en el cual se expondrá el servicio.
* -DserviceTo: endpoint al cual se producirá los mensajes del servicio.
* -DbasicAuth: aplica seguridad básica para el servicio, true con seguridad, false sin seguridad.


El anterior ejercicio generará un nuevo proyecto llamado "middleware-rest-proxy-base", nombre que se le dió al artifactId previamente.

Finalmente, ejecutar el proyecto de la siguiente forma:

	mvn spring-boot:run


### Referencias:
	https://github.com/tmforum/RESTGUIDELINESV2/blob/master/TMF630_REST_API_Design_Guidelines_Part_1_R14.5.0-5.pdf
