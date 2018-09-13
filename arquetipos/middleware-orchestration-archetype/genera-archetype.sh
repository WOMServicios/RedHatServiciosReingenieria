echo "--------------------------------------"
echo "              REDHAT - WOM"
echo "        SHELL generacion arquetipo"
echo "           Iniciando proceso"
echo "--------------------------------------"


echo "Archetype Group Id: cl.wom.middleware.arquetype"
#read AgroupID
export AgroupID=cl.wom.middleware.arquetype
echo "Archetype Artifact Id: middleware-orchestration-archetype"
#read AartifactID
export AartifactID=middleware-orchestration-archetype
echo "Archetype Version: 1.0.0"
#read Aversion
export Aversion=1.0.0
echo "Group Id (cl.wom.services): "
read groupID
echo "Artifact Id (SRVX000X-XXXX): "
read artifactID
echo "Version (1.0.0): "
read version
echo "PortProxy (8888): "
read port

echo "Ejecutar Servicio al finalizar (S/N): "
read execSN
echo "Path a generar artefacto (/rutainstalacion/): "
read path

echo "---------------------------"
echo "        Genero carpeta"
echo "---------------------------"
rm -rf $path/$artifactID

echo "-------------------------------"
echo "    Compilo e instalo arquetipo"
echo "      mvn clean install        "
echo "-------------------------------"
cd middleware-orchestration-archetype
mvn clean install

echo "----------------------------"
echo "      Entro a carpeta"
echo "----------------------------"
cd $path

echo "--------------------------------------------"
echo "      Generando proyecto en base a arquetipo"
echo "--------------------------------------------"
mvn archetype:generate -DarchetypeGroupId=$AgroupID -DarchetypeArtifactId=$AartifactID \
-DarchetypeVersion=$Aversion -DgroupId=$groupID -DartifactId=$artifactID -Dversion=$version -DportProxy=$port \
-DserviceTo=$destinoTo -DinteractiveMode=false -DbasicAuth=false

echo "--------------------------------"
echo "   FIN Generación Arquetipo "
echo "     ruta de ubicación: "
echo "     $path"
echo "--------------------------------"

if [ $execSN = "S" ] || [ $execSN = "s" ]; then
    cd $path/$artifactID
    echo "-----------------------------------"
    echo "Iniciando Artefacto con Spring-boot"
    echo "-----------------------------------"
    mvn clean spring-boot:run -Dserver.port=0
fi
