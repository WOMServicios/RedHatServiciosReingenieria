mvn clean install
cd ..
cd ..
cd services
rm -rf middleware-soap-proxy-base


mvn archetype:generate -DarchetypeGroupId=cl.wom.arquetypes  -DarchetypeArtifactId=middleware-soap-proxy-archetype \
-DarchetypeVersion=1.0.0 -DgroupId=cl.wom.services  -DartifactId=middleware-soap-proxy-base -Dversion=1.0.0 \
-DportProxy=8888  -DresourceService=api-ra-consultaEjecutivos   -DserviceToHost=www.webservicex.net/  \
-DserviceToPort=80 -DserviceToResource=/globalweather.asmx  -DinteractiveMode=false -DbasicAuth=false



cd middleware-soap-proxy-base
mvn clean install -Dmaven.test.skip=true
mvn spring-boot:run
