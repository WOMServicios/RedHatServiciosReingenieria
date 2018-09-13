PoC Redis-Camel
===========================================

Servicio Spring-Boot que expone 3 rutas Camel, las cuales permiten insertar/actualizar, obtener y eliminar datos de una base de datos Redis Standalone

### Consideraciones:

* Este servicio fue testeado contra la base de datos Redis efimera en Openshift 3.6

 

### Requeremientos:
* Spring Boot
* Apache Maven 
* Java SE 8 

Construcción
------------

Para construir e instalar el arquetipo en el repositorio maven se deberá ejecutar la siguiente línea dentro del proyecto.

    mvn clean install


Ejecución desde línea de comando
--------------------------------

Para ejecutar el proyecto de la siguiente forma:

	mvn spring-boot:run

Los endpoints son:

#Insertar/Actualizar

0.0.0.0:8889/insertRedis

Esta operacion inserta el Key "keyTwo" y el valor "valueTwo"

#Obtener

0.0.0.0:8889/getRedis/{KeyID}

Esta operacion recupera el valor de una Key utilizando el parametro de la url

#Eliminar

0.0.0.0:8889/deleteRedis/{KeyID}

Esta operacion elimina una Key utilizando el parametro de la url



