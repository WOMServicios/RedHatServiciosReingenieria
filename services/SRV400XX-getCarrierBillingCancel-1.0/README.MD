Este proyecto utiliza dos variables de entorno para definir las rutas de los archivos de propiedades, 
estas variables de entorno son las siguientes;:

APP_ENV Que describle la ubicacion del archivo application.properties

Para setear estas variables se deben ejecutar las siguientes setencias.
export APP_ENV="/opt/prop/services.properties"

Esto se hace asi porque en el codigo del servicio se le concatena la extension ".properties"













{
   "requestId": "b4af1d2e-cc24-4a53-b4d7-8e54817635b7",
   "bangoTransactionId": "2005897110",
   "merchantTransactionId": "MerchantIdentifier-c0ab9651e9f47809799176b8d325415",
   "paymentProviderTransactionId": "PP-5a5164105ca648ceaef2bf152850304",
   "userId": "bd2bcf08a2894bb7bf67d0631903bf30",
   "amount": 9.99,
   "currency": "CLP",
}