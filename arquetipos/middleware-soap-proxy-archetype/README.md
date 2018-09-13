WOM - SOAP Proxy Apache Camel - Spring Boot
===========================================

Permite la generación de servicios Proxy SOAP los cuales serán desplegados sobre un entorno autocontenido con Spring Boot.

### Consideraciones:

* El arquetipo no generará rutinas de validación o transformación intermedias, éstas deberán ser ajustadas por el programador al momento de generar el servicio.
* El arquetipo siempre responderá a una situación de proxy.
* El arquetipo generará un servicio basado en el modelo "pass through".
* El contrato del servicio deberá ser adecuado dependiendo de las necesidades de interpretación, tanto de transformación como de invocación hacia y desde servicio de destino.

### Requeremientos:
* Spring Boot
* Apache Maven 
* Java SE 8 

Construcción
------------

Para construir e instalar el arquetipo en el repositorio maven se deberá ejecutar la siguiente línea dentro del proyecto.

    mvn clean install

Nota: Con la anterior línea el arquetipo quedará instalado en el repositorio local el cual deberá ser versionado sobre Repositorio Maven correspondiente.

Ejecución desde línea de comando
--------------------------------

Después de instalado el arquetipo en el repositorio local, se deberá ejecutar la siguiente línea 
para la generación del proyecto:

	mvn archetype:generate -DarchetypeGroupId=cl.wom.arquetypes -DarchetypeArtifactId=middleware-soap-proxy-archetype -DarchetypeVersion=1.0.0 -DgroupId=cl.wom.services -DartifactId=middleware-soap-proxy-02 -Dversion=1.0.0 -DportProxy=8888 -DresourceService=/RedkneeSoap_v3_0/services/SubscriptionService/ -DserviceTo=http://10.216.33.96:11648/RedkneeSoap_v3_0/services/SubscriptionService/  -DserviceToHost=127.0.0.1  -DserviceToPort=11648 -DserviceToResource=/RedkneeSoap_v3_0/services/SubscriptionService/ -DinteractiveMode=false -DbasicAuth=false
	
* -Dbusiness: atributo que define el nombre de la ruta camel, que servirá para diferenciarla de las demás rutas existentes.
* -DserviceToHost: nombre o ip del host servicio al cual se dirigirá la petición.
* -DserviceToPort: puerto del host servicio al cual se dirigirá la petición.
* -DserviceToresource: nombre del recurso que quedará expuesto.
* -DresourceService: nombre del recurso que se le dará al servicio expuesto el cual deberá definirse posteriormente se se trata de post o get.

El anterior ejercicio generará un nuevo proyecto llamado "middleware-soap-proxy-base", nombre que se le dió al artifactId previamente.	

Finalmente, ejecutar el proyecto de la siguiente forma:

	mvn spring-boot:run
