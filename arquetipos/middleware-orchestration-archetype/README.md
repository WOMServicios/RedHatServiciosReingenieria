WOM - SOAP Proxy Apache Camel - Spring Boot
=====================================

Permite la generación de servicios Proxy SOAP los cuales serán desplegados sobre un entorno autocontenido con Spring Boot.

### Considerations:

* El arquetipo generará un servicio orquestador, el cual tiene distintas rutas.
* El arquetipo cuenta con llamados a Xpath, condiciones (choice), etc.
* El arquetipo incorpora el manejo de compresión para las respuestas GZIP.
* El arquetipo cuenta con los 5 archivos de propiedades que fueron definidos, obteniendo el que corresponde
según el ambiente.

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

mvn archetype:generate -DarchetypeGroupId=<b>cl.wom.middleware.arquetype</b> -DarchetypeArtifactId=<b>middleware-orchestration-archetype</b> \
-DarchetypeVersion=<b>1.0.0</b> -DgroupId=<b>*cl.wom.services*</b> -DartifactId=<b>*middleware-orquestacion-base*</b> -Dversion=<b>*1.0.0*</b> 
-DportProxy=<b>*8888*</b> -DinteractiveMode=<b>*false*</b>

* -DinteractiveMode: El proyecto es generado en bach mode, no solicita confirmacion de los parametros antes 					ingresados.

El ejercicio anterior crearaá un nuevo proyecto llamado <b>"middleware-orquestacion-base"</b>, nombre que se le dió al atributo <b>artifactId</b> previamente.

Finalmente, ejecutar el proyecto de la siguiente forma:

	mvn spring-boot:run