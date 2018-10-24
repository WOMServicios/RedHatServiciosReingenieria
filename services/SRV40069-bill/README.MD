Test Unitario Sobre
curl 'http://0.0.0.0:8888/customerBill/bill/?bill.accountId=string&bill.quantity=string'


db.bill.find({ accountId: "string", billNro: "string"}, {_id: 0})



Este proyecto utiliza dos variables de entorno para definir las rutas de los archivos de propiedades, 
estas variables de entorno son las siguientes;:

APP_ENV Que describle la ubicacion del archivo application.properties

Para setear estas variables se deben ejecutar las siguientes setencias.
export APP_ENV="/opt/prop/services.properties"

importante comprobar que el archivo services.properties se encuentre en la ruta
