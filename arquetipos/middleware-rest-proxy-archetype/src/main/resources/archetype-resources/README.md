WOM - SOAP Proxy Apache Camel - Spring Boot
=====================================

Permite la generaciónd de servicios Proxy SOAP los cuales serán desplegados sobre un entorno autocontenido con Spring Boot.

### Considerations:

* El arquetipo no generará rutinas de validación o tranformación intermedias, éstas deberán ser ajustadas por el programador al momento de generar el servicio
* El arquetipo siempre responderá a una situación de proxy
* El arquetipo generará un servicio basado en el modelo "pass through"
* El contrato del servicio deberá ser adecuado dependiendo de las necesidades de interpretación, tanto de transformación como de invocación hacia y desde servicio de destino

### Requirements:
* Spring Boot
* Apache Maven 
* Java SE 8 

Building
--------

Para construir e instalar el arquetipo en el repositorio maven se deberá ejecutar la siguiente línea dentro del proyecto

    mvn clean install

Nota: Con la anterior línea el arquetipo quedará instalado en el repositorio local el cual deberá ser versionado sobre Repositorio Maven correspondiente.

Running from the command line
-----------------------------

Después de instalado el arquetipo en el repositorio local, se deberá ejecutar la siguiente línea 
para la generación del proyecto.

mvn archetype:generate -DarchetypeGroupId=cl.wom.middleware.arquetype -DarchetypeArtifactId=middleware-soap-proxy-archetype \
-DarchetypeVersion=1.0.0 -DgroupId=cl.wom.soap -DartifactId=middleware-ra-proxy-soap-base -Dversion=1.0.0 -DportProxy=8888 \ 
-DresourceService=api-ra-consultaEjecutivos -DserviceTo=http://www.webservicex.net/globalweather.asmx  -DinteractiveMode=false \
-DbasicAuth=false
	
* -Dbusiness: atributo que define el nombre de la ruta camel, que servira para diferenciarla de las demas rutas existentes
* -DserviceTo: nombre del servicio al cual se dirigirá la petición
* -DresourceService: nombre del recurso que se le dará al servicio expuesto el cual deberá definirse posteriormente se se trata de post o get

El anterior ejercicio generará un nuevo proyecto llamado "middleware-ra-proxy-soap-base", nombre que se le dió al artifactId previamente.

mvn spring-boot:run
