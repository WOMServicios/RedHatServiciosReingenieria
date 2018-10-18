Este proyecto utiliza dos variables de entorno para definir las rutas de los archivos de propiedades, 
estas variables de entorno son las siguientes;:

APP_ENV Que describle la ubicacion del archivo application.properties
SQL_ENV Que describe la ubicacion del archivo sql.properties

Para setear estas variables se deben ejecutar las siguientes setencias.
export APP_ENV="application"

export APP_ENV="/opt/prop/services.properties"

Esto se hace asi porque en el codigo del servicio se le concatena la extension ".properties"