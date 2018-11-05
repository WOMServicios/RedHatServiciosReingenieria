Este proyecto utiliza dos variables de entorno para definir las rutas de los archivos de propiedades, 
estas variables de entorno son las siguientes;:

APP_ENV Que describle la ubicacion del archivo application.properties
SQL_ENV Que describe la ubicacion del archivo sql.properties

Para setear estas variables se deben ejecutar las siguientes setencias.

export APP_ENV="/opt/prop/services.properties"

Esto se hace asi porque en el codigo del servicio se le concatena la extension ".properties"


{  
   "requestId":"b4af1d2e-cc24-4a53-b4d7-8e54817635b7",
   "bangoTransactionId":"2005897110",
   "merchantTransactionId":"MerchantIdentifier-c0ab9651e9f47809799176b8d325415",
   "paymentProviderTransactionId":"WOM_AUTHORIZE_20181029151052_79",
   "userId":"2496423",
   "amount":5990,
   "currency":"CLP"
}