#!/bin/bash

#set -o nounset
#set -o errexit

colblk='\033[0;30m' # Black - Regular
colred='\033[0;31m' # Red
colgrn='\033[0;32m' # Green
colylw='\033[0;33m' # Yellow
colpur='\033[0;35m' # Purple
colrst='\033[0m'    # Text Reset
 
verbosity=4
 
### verbosity levels
silent_lvl=0
crt_lvl=1
err_lvl=2
wrn_lvl=3
ntf_lvl=4
inf_lvl=5
dbg_lvl=6
 
## esilent prints output even in silent mode
function esilent () { verb_lvl=$silent_lvl elog "$@" ;}
function enotify () { verb_lvl=$ntf_lvl elog "$@" ;}
function eok ()    { verb_lvl=$ntf_lvl elog "SUCCESS - $@" ;}
function ewarn ()  { verb_lvl=$wrn_lvl elog "${colylw}WARNING${colrst} - $@" ;}
function einfo ()  { verb_lvl=$inf_lvl elog "${colwht}INFO${colrst} ---- $@" ;}
function edebug () { verb_lvl=$dbg_lvl elog "${colgrn}DEBUG${colrst} --- $@" ;}
function eerror () { verb_lvl=$err_lvl elog "${colred}ERROR${colrst} --- $@" ;}
function ecrit ()  { verb_lvl=$crt_lvl elog "${colpur}FATAL${colrst} --- $@" ;}
function edumpvar () { for var in $@ ; do edebug "$var=${!var}"; done }
function elog() {
  if [ $verbosity -ge $verb_lvl ]; then
    datestring=`date +"%Y-%m-%d %H:%M:%S"`
    echo -e "$datestring - $@"
  fi
}

if [ "${1}" = "compile" ];
then
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -g|--git-repo)
    GITREPO="$2"
    shift # past argument
    shift # past value
    ;;
    -b|--branch)
    GITBRANCH="$2"
    shift # past argument
    shift # past value
    ;;
    -s|--settings)
    SETTINGS="$2"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)    # unknown option
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters

  if [ -z "$GITREPO" ];
  then
    eerror "Debes indicar el repositorio git. Ejemplo: -g https://github.com/ejemplo.git";
    exit 1;x
  fi

  enotify "Se procede a clonar el proyecto en el repositorio: $GITREPO.";

  if [ -z "$GITBRANCH" ];
  then
    eerror "Debes indicar el branch del repositorio git. Ejemplo: -b master";
    exit 1;
  fi

  enotify "Y en el branch: $GITBRANCH.";

  if [ -d "binary" ];
  then
    # enotify "Carpeta binary existe, por lo que se elimina para clonar denuevo.";
    rm -rf binary/
  fi

  if [ -f "binary.jar" ];
  then
    # enotify "Archivo binary.jar existe, por lo que se elimina para generar el nuevo jar.";
    rm -rf binary.jar
  fi

  git clone $GITREPO --branch $GITBRANCH binary && cd binary

  if [ ! -f "pom.xml" ];
  then
    eerror "El código fuente seleccionado no es un proyecto Java, o no existe el pom.xml";
    exit 1;
  fi

  if [ -n "$SETTINGS" ];
  then
    enotify "Se procede a compilar los fuentes con el settings: $SETTINGS.";
    mvn clean package -s $SETTINGS
  else
    enotify "Se procede a compilar los fuentes.";
    mvn clean package
  fi

  BINARY="$(ls -A1 target/ | grep ".jar$")";

  if [ -z "$BINARY" ];
  then
    eerror "Compilación a finalizado ya que no encontró el JAR de resultado.";
    exit 1;
  fi

  enotify "Se copia binario como resultado de la compilación con el nombre binary.jar";
  cp target/$BINARY ../binary.jar

  cd .. && rm -rf binary/

  exit 0;
fi

if [ "${1}" = "build" ];
then
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -t|--tag)
    TAG="$2"
    shift # past argument
    shift # past value
    ;;
    -v|--version)
    VERSION="$2"
    shift # past argument
    shift # past value
    ;;
    -d|--dockerfile)
    DOCKERFILE="$2"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)    # unknown option
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters

  if [ -z "$TAG" ];
  then
    eerror "Debes indicar el tag de la imagen de contenedor a generar. Ejemplo: -t wom-project/app";
    exit 1;
  fi

  if [ -z "$VERSION" ];
  then
    eerror "Debes indicar la versión de la imagen de contenedor a generar. Ejemplo: -v 1.0";
    exit 1;
  fi

  if [ -n "$DOCKERFILE" ];
  then
    wget $DOCKERFILE -o=Dockerfile
  fi

  if [ ! -f "Dockerfile" ];
  then
    eerror "Archivo Dockerfile no encontrado.";
    exit 1;
  fi

  docker build -t $TAG:$VERSION .
  exit 0;
fi

if [ "${1}" = "push" ];
then
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -i|--image)
    IMAGE="$2"
    shift # past argument
    shift # past value
    ;;
    -t|--token)
    TOKEN="$2"
    shift # past argument
    shift # past value
    ;;
    -r|--registry)
    REGISTRY="$2"
    shift # past argument
    shift # past value
    ;;
    -v|--version)
    VERSION="$2"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)    # unknown option
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters

  if [ -z "$IMAGE" ];
  then
    eerror "Debes indicar el tag o nombre de la imagen de contenedor. Ejemplo: -i wom-project/app";
    exit 1;
  fi

  if [ -z "$VERSION" ];
  then
    eerror "Debes indicar la versión de la imagen de contenedor. Ejemplo: -v 1.0";
    exit 1;
  fi

  if [ -z "$TOKEN" ];
  then
    eerror "Debes indicar el token de acceso al API de OCP de destino. Ejemplo: -t jHJd2763bm";
    exit 1;
  fi

  if [ -z "$REGISTRY" ];
  then
    eerror "Debes indicar la URL del registry integrado al OCP de destino. Ejemplo: -t jHJd2763bm";
    exit 1;
  fi

  if [ -z "$(docker images --format "{{.Repository}}:{{.Tag}}" | grep $IMAGE:$VERSION)" ];
  then
    eerror "Par imagen versión no encontrado.";
    exit 1;
  fi

  enotify "Se hace la autenticación en el registry $REGISTRY con el token $TOKEN";

  if [ "$(edebug $TOKEN | docker login -u unused --password-stdin $REGISTRY)" != "Login Succeeded" ];
  then
    eerror "No fue posible autenticarse en el registry: $REGISTRY, con el token: $TOKEN.";
    exit 1;
  fi

  docker tag $IMAGE:$VERSION $REGISTRY/$IMAGE:$VERSION
  docker push $REGISTRY/$IMAGE:$VERSION
  docker logout $REGISTRY
  exit 0;
fi

if [ "${1}" = "deploy" ];
then
  LABEL=""
  ENVSPARAMS=""
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -t|--token)
    TOKEN="$2"
    shift # past argument
    shift # past value
    ;;
    -m|--master)
    MASTER="$2"
    shift # past argument
    shift # past value
    ;;
    --project-id)
    PROJECT="$2"
    shift # past argument
    shift # past value
    ;;
    -i|--image)
    IMAGE="$2"
    shift # past argument
    shift # past value
    ;;
    -v|--version)
    VERSION="$2"
    shift # past argument
    shift # past value
    ;;
    -n|--name)
    NAME="$2"
    shift # past argument
    shift # past value
    ;;
    --metodology)
    METODOLOGY="$2"
    shift # past argument
    shift # past value
    ;;
    -e)
    ENVSPARAMS="$ENVSPARAMS -e $2"
    shift # past argument
    shift # past value
    ;;
    --env-file)
    ENVFILE="$2"
    shift # past argument
    shift # past value
    ;;
    --environment)
    ENVIRONMENT="$2"
    shift # past argument
    shift # past value
    ;;
    -g|--git-repo)
    GITREPO="$2"
    shift # past argument
    shift # past value
    ;;
    -b|--branch)
    GITBRANCH="$2"
    shift # past argument
    shift # past value
    ;;
    --template)
    TEMPLATE="$2"
    shift # past argument
    shift # past value
    ;;
    --params-file)
    PARAMFILE="$2"
    shift # past argument
    shift # past value
    ;;
    -l|--label)
    LABEL="$LABEL,$2"
    shift # past argument
    shift # past value
    ;;
    --hostname)
    HOSTNAME="$2"
    shift # past argument
    shift # past value
    ;;
    --path)
    CONTEXTO="$2"
    shift # past argument
    shift # past value
    ;;
    --port)
    PORT="$2"
    shift # past argument
    shift # past value
    ;;
    --expose-name)
    EXPOSENAME="$2"
    shift # past argument
    shift # past value
    ;;
    --cpu)
    CPULIMIT="$2"
    shift # past argument
    shift # past value
    ;;
    --memory)
    MEMLIMIT="$2"
    shift # past argument
    shift # past value
    ;;
    --min)
    MINIMO="$2"
    shift # past argument
    shift # past value
    ;;
    --max)
    MAXIMO="$2"
    shift # past argument
    shift # past value
    ;;
    --porcentaje)
    PERCENT="$2"
    shift # past argument
    shift # past value
    ;;
    --from-file)
    FILEPROPERTIES="$2"
    shift # past argument
    shift # past value
    ;;
    --content)
    LITERAL="$2"
    shift # past argument
    shift # past value
    ;;
    --mount-path)
    MOUNTPATH="$2"
    shift # past argument
    shift # past value
    ;;
    --default-format)
    DEFAULTFORMAT="true"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)    # unknown option
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters

  verbosity=6

  einfo "Se dejó el nivel de logs al máximo para ver en detalle, para cambiarlo comentar linea 445."

  if [ -z "$TOKEN" ];
  then
    eerror "Debes indicar el token de acceso al cluster OCP. Ejemplo: -t 23nDSda231.";
    exit 1;
  fi

  if [ -z "$MASTER" ];
  then
    eerror "Debes indicar la URI de acceso al cluster OCP. Ejemplo: -m https://master.novaltepre.corp:8443.";
    exit 1;
  fi

  oc logout

  oc login --token $TOKEN --server $MASTER

  if [ -z "$PROJECT" ];
  then
    eerror "Debes indicar el id de proyecto OCP donde se hará el despliegue. Ejemplo: --project-id dev-account.";
    exit 1;
  fi

  if [ -z "$(oc projects -q | grep "^$PROJECT$")" ];
  then
    eerror "Projecto con ID $PROJECT no existe.";
    oc new-project $PROJECT;
  
  fi

  oc project $PROJECT

  if [ -n "$NAME" ];
  then
    enotify "La aplicación o servicio $NAME ya está desplegado en el proyecto $PROJECT"

    edebug "Se busca el buildconfig con nombre $NAME"

    BC="`oc get bc -o=custom-columns=NAME:.metadata.name --no-headers | grep $NAME`"

    edebug "Se busca el deploymentconfig con nombre $NAME"
    
    DC="$(oc get dc -o=custom-columns=NAME:.metadata.name --no-headers | grep $NAME)"

    einfo "BuildConfig: $BC - DeploymentConfig: $DC"

    edebug "Si el buildconfig con nombre $NAME existe entonces se procede a iniciar el proceso de compilación"
    if [ "$BC" = "$NAME" ];
    then
      oc start-build $NAME --wait --follow

      sleep 1m

      if [ -n "$CPULIMIT" -o -n "$MEMORYLIMIT" ];
      then
        enotify "Asignando limites a despliegue. CPU = $CPULIMIT - Memory = $MEMORYLIMIT."
        LIMITS=""

        if [ -n "$CPULIMIT" ]
        then
          LIMITS="$LIMITS,cpu=$CPULIMIT"
        fi

        if [ -n "$MEMLIMIT" ]
        then
          LIMITS="$LIMITS,memory=$MEMLIMIT"
        fi

        CODELIMIT="oc set resources deploymentconfig $DC --limits=${LIMITS:1}"

        edebug $CODELIMIT
        eval $CODELIMIT
      fi

      if [ -n "$MINIMO" -a -n "$MAXIMO" -a -n "$PERCENT" ]
      then
        if [ "$(oc get hpa | grep  $NAME | awk '{print $1}')" != "$NAME" ]
        then
          CODEAUTOSCALE="oc autoscale dc $NAME --min=$MINIMO --max=$MAXIMO --cpu-percent=$PERCENT --name=$NAME"
        else
          CODEAUTOSCALE="oc patch hpa $NAME -p '{ \"spec\": { \"minReplicas\": $MIN, \"maxReplicas\": $MAX, \"targetCPUUtilizationPercentage\": $PERCENT }}'"
        fi

        edebug $CODEAUTOSCALE
        eval $CODEAUTOSCALE
      fi

      COUNT=0

      while [ "$(oc deploy $DC | head -n 1 | awk '{print $4}')" = "pending" -o "$(oc deploy $DC | head -n 1 | awk '{print $4}')" = "running" ]; do
        sleep 30s
        COUNT+=1
        enotify "Intento #$COUNT - El despliegue está en estado: $(oc deploy $DC | head -n 1 | awk '{print $4}')"
        if [ $COUNT -ge 10 ];
        then
          break
        fi
      done

      oc logs dc/$DC

      enotify "El despliegue terminó en estado: $(oc deploy $DC | head -n 1 | awk '{print $4}')"

      exit 0;
    fi
    
    edebug "Si el deploymentconfig con nombre $NAME existe entonces se procede a iniciar el proceso de despliegue"

    if [ "$DC" = "$NAME" ];
    then
      oc rollout latest $NAME

      if [ -n "$CPULIMIT" -o -n "$MEMORYLIMIT" ];
      then
        enotify "Asignando limites a despliegue. CPU = $CPULIMIT - Memory = $MEMORYLIMIT."
        LIMITS=""

        if [ -n "$CPULIMIT" ]
        then
          LIMITS="$LIMITS,cpu=$CPULIMIT"
        fi

        if [ -n "$MEMLIMIT" ]
        then
          LIMITS="$LIMITS,memory=$MEMLIMIT"
        fi

        CODELIMIT="oc set resources deploymentconfig $DC --limits=${LIMITS:1}"

        edebug $CODELIMIT
        eval $CODELIMIT
      fi

      if [ -n "$MINIMO" -a -n "$MAXIMO" -a -n "$PERCENT" ]
      then
        if [ "$(oc get hpa | grep  $NAME | awk '{print $1}')" != "$NAME" ]
        then
          CODEAUTOSCALE="oc autoscale dc $NAME --min=$MINIMO --max=$MAXIMO --cpu-percent=$PERCENT --name=$NAME"
        else
          CODEAUTOSCALE="oc patch hpa $NAME -p '{ \"spec\": { \"minReplicas\": $MIN, \"maxReplicas\": $MAX, \"targetCPUUtilizationPercentage\": $PERCENT }}'"
        fi

        edebug $CODEAUTOSCALE
        eval $CODEAUTOSCALE
      fi

      COUNT=0

      while [ "$(oc deploy $DC | head -n 1 | awk '{print $4}')" = "pending" -o "$(oc deploy $DC | head -n 1 | awk '{print $4}')" = "running" ]; do
        sleep 30s
        COUNT+=1
        enotify "Intento #$COUNT - El despliegue está en estado: $(oc deploy $DC | head -n 1 | awk '{print $4}')"
        if [ $COUNT -ge 10 ];
        then
          break
        fi
      done

      oc logs dc/$DC

      enotify "El despliegue terminó en estado: $(oc deploy $DC | head -n 1 | awk '{print $4}')"

      exit 0;
    fi
  else
    eerror "Debes indicar el nombre del despliegue. Ejemplo: -n srv902731 o --name srv902731";
    exit 1;
  fi

  if [ -z "$ENVIRONMENT" ];
  then
    eerror "Debes indicar el id del ambiente OCP donde se hará el despliegue. Posibles valores: develop, uat, training, hotfix. En caso de produccion debe ser primary. Ejemplo: --environment uat.";
    exit 1;
  fi

  if [ "$ENVIRONMENT" != "develop" ] && [ "$ENVIRONMENT" != "uat" ] && [ "$ENVIRONMENT" != "training" ] && [ "$ENVIRONMENT" != "hotfix" ];
  then
    eerror "Debes indicar un id del ambiente OCP válido. Posibles valores: develop, uat, training, hotfix. En caso de produccion debe ser primary.";
    exit 1;
  fi

  if [ "$ENVFILE" != "" -a "$ENVSPARAMS" != "" ]; then
    enotify "Solo se puede declarar variables de entorno con archivo o por argumento. Ejemplo: --env-file env.txt o -e USER=vsts -e AUTHOR=\"Caio\ Medeiros\"...";
    exit 1;
  fi

  if [ -z "$METODOLOGY" ];
  then
    eerror "Debes indicar una metodología de despliegue. Valores posibles: docker-image, git-repo o template, Ejemplo: --metodology docker-image".
    exit 1;
  fi

  if [ "$METODOLOGY" = "docker-image" ];
  then
    if [ -z "$NAME" ];
    then
      eerror "Debes indicar el nombre que tendrá el despliegue. Ejemplo: -n svr281723.";
      exit 1;
    fi

    if [ -z "$IMAGE" ];
    then
      eerror "Debes indicar el tag o nombre de la imagen a utilizar para el despliegue. Ejemplo: -i wom-project/app.";
      exit 1;
    fi

    if [ -z "$VERSION" ];
    then
      eerror "Debes indicar la version de la imagen a utilizar para el despliegue. Ejemplo: -v 1.0.";
      exit 1;
    fi

    if [ -f "$ENVFILE" ];
    then
      CODE="oc new-app $IMAGE:$VERSION --name=$NAME --env-file=$ENVFILE -l=\"${LABEL:1}\""
    elif [ -n "$ENVSPARAMS" ]; then
      CODE="oc new-app $IMAGE:$VERSION --name=$NAME $ENVSPARAMS -l=\"${LABEL:1}\""
    else
      CODE="oc new-app $IMAGE:$VERSION --name=$NAME -l=\"${LABEL:1}\""
    fi

    edebug $CODE
    eval $CODE

   # oc patch dc $NAME -p '{"spec":{"template":{"spec":{"nodeSelector":{"region":"'"$ENVIRONMENT"'","environment":"'"$ENVIRONMENT"'"}}}}}'

    if [ -n "$PORT" ]
    then
      READINESSCODE="oc set probe dc/$NAME --readiness --open-tcp=$PORT --initial-delay-seconds=20 --timeout-seconds=1"
      LIVENESSCODE="oc set probe dc/$NAME --liveness --open-tcp=$PORT --initial-delay-seconds=180 --timeout-seconds=1"

      edebug $READINESSCODE
      eval $LIVENESSCODE
    fi

    if [ -n "$CPULIMIT" -o -n "$MEMORYLIMIT" ];
    then
      enotify "Asignando limites a despliegue. CPU = $CPULIMIT - Memory = $MEMORYLIMIT."
      LIMITS=""

      if [ -n "$CPULIMIT" ]
      then
        LIMITS="$LIMITS,cpu=$CPULIMIT"
      fi

      if [ -n "$MEMLIMIT" ]
      then
        LIMITS="$LIMITS,memory=$MEMLIMIT"
      fi

      CODELIMIT="oc set resources deploymentconfig $NAME --limits=${LIMITS:1}"

      edebug $CODELIMIT
      eval $CODELIMIT
    fi

    if [ -n "$MINIMO" -a -n "$MAXIMO" -a -n "$PERCENT" ]
    then
      if [ "$(oc get hpa | grep  $NAME | awk '{print $1}')" != "$NAME" ]
      then
        CODEAUTOSCALE="oc autoscale dc $NAME --min=$MINIMO --max=$MAXIMO --cpu-percent=$PERCENT --name=$NAME"
      else
        CODEAUTOSCALE="oc patch hpa $NAME -p '{ \"spec\": { \"minReplicas\": $MIN, \"maxReplicas\": $MAX, \"targetCPUUtilizationPercentage\": $PERCENT }}'"
      fi

      edebug $CODEAUTOSCALE
      eval $CODEAUTOSCALE
    fi

    if [ -n "$MOUNTPATH" -a -n "$FILEPROPERTIES" ] || [ -n "$MOUNTPATH" -a -n "$LITERAL" ] || [ -n "$MOUNTPATH" -a -n "$DEFAULTFORMAT" ]
    then
      if [ "$(oc get configmap | grep -c $NAME | awk '{print $1}')" = "$NAME" ]
      then
        enotify "El archivo de propriedades ya esta fue creado"
        
        CODEPROPERTIES="oc volume dc $NAME --overwrite --add -t configmap --mount-path $MOUNTPATH --name=properties-volume --configmap-name=$NAME-properties"
        
        edebug $CODEPROPERTIES
        eval $CODEPROPERTIES

      else
        if [ "$DEFAULTFORMAT" = "true" ]
        then
          enotify "Se utilizará el formato por defecto, se considera que tienes declarado las siguientes variables declaradas en el entorno: LOGLEVEL, USER_RK, PASS_RK, BACK_HOST, BACK_PORT, BACK_URI, SRV_ID, OPERATION_ID, SRV_HOST, SRV_PORT."
          CODEPROPERTIES="oc create -f - <<API
          apiVersion: v1
          metadata:
            name: $NAME-properties
          data:
            $NAME.properties: |-
              logging.level.= ${LOGLEVEL}
              camel.timeout.redelivery-delay=3000
              camel.timeout.max-delivery=4
              camel.soap.basicauth.user=${USER_RK}
              camel.soap.basicauth.pass=${PASS_RK}
              soap.service.host=${BACK_HOST}
              soap.service.port= ${BACK_PORT}
              soap.service.resource=${BACK_URI}
              orquestacion.service=${SRV_ID}
              orquestacion.operacion=${OPERATION_ID}
              orquestacion.endpoint=${SRV_HOST}
              orquestacion.puerto=${SRV_PORT}
          kind: ConfigMap
          API"
        else
          if [ -n "$FILEPROPERTIES" ]
          then
            CODEPROPERTIES="oc create configmap $NAME-properties --from-file=$NAME.properties=$FILEPROPERTIES"
          else
            CODEPROPERTIES="oc create configmap $NAME-properties --from-literal=$NAME.properties=$LITERAL"
          fi
        fi
        

        edebug $CODEPROPERTIES
        eval $CODEPROPERTIES

        CODEPROPERTIES="oc volume dc $NAME --overwrite --add -t configmap --mount-path $MOUNTPATH --name=properties-volume --configmap-name=$NAME-properties"
          
        edebug $CODEPROPERTIES
        eval $CODEPROPERTIES
      fi
    fi

    COUNT=0

    while [ "$(oc deploy $NAME | head -n 1 | awk '{print $4}')" = "pending" -o "$(oc deploy $NAME | head -n 1 | awk '{print $4}')" = "running" ]; do
      sleep 30s
      COUNT+=1
      enotify "Intento #$COUNT - El despliegue está en estado: $(oc deploy $NAME | head -n 1 | awk '{print $4}')"
      if [ $COUNT -ge 10 ];
      then
        break
      fi
    done

    oc logs dc/$NAME

    enotify "El despliegue terminó en estado: $(oc deploy $NAME | head -n 1 | awk '{print $4}')"

    if [ -n "$HOSTNAME" ]
    then
      EXPOSECODE="oc expose svc $NAME --hostname=$HOSTNAME -l=\"${LABEL:1}\""

      if [ -n "$PORT" ]
      then
        EXPOSECODE="$EXPOSECODE --port=$PORT"
      fi

      if [ -n "$CONTEXTO" ]
      then
        EXPOSECODE="$EXPOSECODE --path=$CONTEXTO"
      fi

      if [ -n "$EXPOSENAME" ];
      then
        EXPOSECODE="$EXPOSECODE --name=$EXPOSENAME"
      fi

      edebug $EXPOSECODE
      eval $EXPOSECODE
    fi

  elif [ "$METODOLOGY" = "git-repo" ];
  then
    if [ -z "$NAME" ];
    then
      eerror "Debes indicar el nombre que tendrá el despliegue. Ejemplo: -n svr281723.";
      exit 1;
    fi

    if [ -z "$IMAGE" ];
    then
      eerror "Debes indicar el tag o nombre de la imagen a utilizar para el despliegue. Ejemplo: -i wom-project/app.";
      exit 1;
    fi

    if [ -z "$VERSION" ];
    then
      eerror "Debes indicar la version de la imagen a utilizar para el despliegue. Ejemplo: -v 1.0.";
      exit 1;
    fi

    if [ -z "$(oc get is -n openshift | grep $IMAGE)" && -z "$(oc get is -n $PROJECT | grep $IMAGE)" ];
    then
      eerror "Par imagen versión no encontrado.";
      exit 1;
    fi

    if [ -z "$GITREPO" ];
    then
      eerror "Debes indicar el repositorio git del código fuente a desplegar. Ejemplo: -g https://github.com/ejemplo.git.";
      exit 1;
    fi

    if [ -z "$GITBRANCH" ];
    then
      eerror "Debes indicar la rama git del código fuente a desplegar. Ejemplo: -b master.";
      exit 1;
    fi

    git clone $GITREPO --branch $GITBRANCH binary && cd binary

    if [ -f "../$ENVFILE" ];
    then
      CODE="oc new-app . --docker-image=$IMAGE:$VERSION --name=$NAME --env-file=../$ENVFILE -l=\"${LABEL:1}\""
    elif [ -n "$ENVSPARAMS" ]; then
      CODE="oc new-app . --docker-image=$IMAGE:$VERSION --name=$NAME $ENVSPARAMS -l=\"${LABEL:1}\""
    else
      CODE="oc new-app . --docker-image=$IMAGE:$VERSION --name=$NAME -l=\"${LABEL:1}\""
    fi

    edebug $CODE
    eval $CODE

    #oc patch dc $NAME -p '{"spec":{"template":{"spec":{"nodeSelector":{"region":"'"$ENVIRONMENT"'","environment":"'"$ENVIRONMENT"'"}}}}}'

    if [ -n "$HOSTNAME" ]
    then
      EXPOSECODE="oc expose svc $NAME --hostname=$HOSTNAME -l=\"${LABEL:1}\""

      if [ -n "$PORT" ]
      then
        EXPOSECODE="$EXPOSECODE --port=$PORT"
      fi

      if [ -n "$CONTEXTO" ]
      then
        EXPOSECODE="$EXPOSECODE --path=$CONTEXTO"
      fi

      if [ -n "$EXPOSENAME" ];
      then
        EXPOSECODE="$EXPOSECODE --name=$EXPOSENAME"
      fi

      edebug $EXPOSECODE
      eval $EXPOSECODE
    fi

    if [ -n "$PORT" ]
    then
      READINESSCODE="oc set probe dc/$NAME --readiness --open-tcp=$PORT --initial-delay-seconds=20 --timeout-seconds=1"
      LIVENESSCODE="oc set probe dc/$NAME --liveness --open-tcp=$PORT --initial-delay-seconds=180 --timeout-seconds=1"

      edebug $READINESSCODE
      eval $LIVENESSCODE
    fi

    if [ -n "$CPULIMIT" -o -n "$MEMORYLIMIT" ];
    then
      enotify "Asignando limites a despliegue. CPU = $CPULIMIT - Memory = $MEMORYLIMIT."
      LIMITS=""

      if [ -n "$CPULIMIT" ]
      then
        LIMITS="$LIMITS,cpu=$CPULIMIT"
      fi

      if [ -n "$MEMLIMIT" ]
      then
        LIMITS="$LIMITS,memory=$MEMLIMIT"
      fi

      CODELIMIT="oc set resources deploymentconfig $NAME --limits=${LIMITS:1}"

      edebug $CODELIMIT
      eval $CODELIMIT
    fi

    if [ -n "$MINIMO" -a -n "$MAXIMO" -a -n "$PERCENT" ]
    then
      if [ "$(oc get hpa | grep  $NAME | awk '{print $1}')" != "$NAME" ]
      then
        CODEAUTOSCALE="oc autoscale dc $NAME --min=$MINIMO --max=$MAXIMO --cpu-percent=$PERCENT --name=$NAME"
      else
        CODEAUTOSCALE="oc patch hpa $NAME -p '{ \"spec\": { \"minReplicas\": $MIN, \"maxReplicas\": $MAX, \"targetCPUUtilizationPercentage\": $PERCENT }}'"
      fi

      edebug $CODEAUTOSCALE
      eval $CODEAUTOSCALE
    fi

    if [ -n "$MOUNTPATH" -a -n "$FILEPROPERTIES" ] || [ -n "$MOUNTPATH" -a -n "$LITERAL" ] || [ -n "$MOUNTPATH" -a -n "$DEFAULTFORMAT" ]
    then
      if [ "$(oc get configmap | grep -c $NAME | awk '{print $1}')" = "$NAME" ]
      then
        enotify "El archivo de propriedades ya esta fue creado"
        
        CODEPROPERTIES="oc volume dc $NAME --overwrite --add -t configmap --mount-path $MOUNTPATH --name=properties-volume --configmap-name=$NAME-properties"
        
        edebug $CODEPROPERTIES
        eval $CODEPROPERTIES

      else
        if [ "$DEFAULTFORMAT" = "true" ]
        then
          enotify "Se utilizará el formato por defecto, se considera que tienes declarado las siguientes variables declaradas en el entorno: LOGLEVEL, USER_RK, PASS_RK, BACK_HOST, BACK_PORT, BACK_URI, SRV_ID, OPERATION_ID, SRV_HOST, SRV_PORT."
          CODEPROPERTIES="oc create -f - <<API
          apiVersion: v1
          metadata:
            name: $NAME-properties
          data:
            $NAME.properties: |-
              logging.level.= ${LOGLEVEL}
              camel.timeout.redelivery-delay=3000
              camel.timeout.max-delivery=4
              camel.soap.basicauth.user=${USER_RK}
              camel.soap.basicauth.pass=${PASS_RK}
              soap.service.host=${BACK_HOST}
              soap.service.port= ${BACK_PORT}
              soap.service.resource=${BACK_URI}
              orquestacion.service=${SRV_ID}
              orquestacion.operacion=${OPERATION_ID}
              orquestacion.endpoint=${SRV_HOST}
              orquestacion.puerto=${SRV_PORT}
          kind: ConfigMap
          API"
        else
          if [ -n "$FILEPROPERTIES" ]
          then
            CODEPROPERTIES="oc create configmap $NAME-properties --from-file=$NAME.properties=$FILEPROPERTIES"
          else
            CODEPROPERTIES="oc create configmap $NAME-properties --from-literal=$NAME.properties=$LITERAL"
          fi
        fi
        

        edebug $CODEPROPERTIES
        eval $CODEPROPERTIES

        CODEPROPERTIES="oc volume dc $NAME --overwrite --add -t configmap --mount-path $MOUNTPATH --name=properties-volume --configmap-name=$NAME-properties"
          
        edebug $CODEPROPERTIES
        eval $CODEPROPERTIES
      fi
    fi

    COUNT=0

    while [ "$(oc deploy $NAME | head -n 1 | awk '{print $4}')" = "pending" -o "$(oc deploy $NAME | head -n 1 | awk '{print $4}')" = "running" ]; do
      sleep 30s
      COUNT+=1
      enotify "Intento #$COUNT - El despliegue está en estado: $(oc deploy $NAME | head -n 1 | awk '{print $4}')"
      if [ $COUNT -ge 10 ];
      then
        break
      fi
    done

    oc logs dc/$NAME

    enotify "El despliegue terminó en estado: $(oc deploy $NAME | head -n 1 | awk '{print $4}')"

    cd .. && rm -rf binary/
  elif [ "$METODOLOGY" = "template" ];
  then
    if [ -z "$TEMPLATE" ];
    then
      eerror "Debes indicar el ID del template a usar. Ejemplo: --template fis-java-openshift-hawkular.";
      exit 1;
    fi

    if [ -z "$PARAMFILE" ];
    then
      eerror "Debes indicar el archivo de parámetros que utiliza el template seleccionado. Ejemplo: --params-file params.txt.";
      exit 1;
    fi

    CODE="oc new-app --template=$TEMPLATE --param-file=$PARAMFILE -l=\"${LABEL:1}\""
    edebug $CODE
    eval $CODE
  else
    eerror "Debes indicar una metodología de despliegue. Valores posibles: docker-image, git-repo o template".
    exit 1;
  fi

  oc logout

  exit 0;
fi

if [ "${1}" = "expose" ];
then
  LABEL=""
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -t|--token)
    TOKEN="$2"
    shift # past argument
    shift # past value
    ;;
    -m|--master)
    MASTER="$2"
    shift # past argument
    shift # past value
    ;;
    --project-id)
    PROJECT="$2"
    shift # past argument
    shift # past value
    ;;
    -h|--hostname)
    HOSTNAME="$2"
    shift # past argument
    shift # past value
    ;;
    --port)
    PORT="$2"
    shift # past argument
    shift # past value
    ;;
    --path)
    CONTEXTO="$2"
    shift # past argument
    shift # past value
    ;;
    -n|--name)
    NAME="$2"
    shift # past argument
    shift # past value
    ;;
    --expose-name)
    EXPOSENAME="$2"
    shift # past argument
    shift # past value
    ;;
    -l|--label)
    LABEL="$LABEL,$2"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters

  if [ -z "$TOKEN" ];
  then
    eerror "Debes indicar el token de acceso al cluster OCP. Ejemplo: -t 23nDSda231.";
    exit 1;
  fi

  if [ -z "$MASTER" ];
  then
    eerror "Debes indicar la URI de acceso al cluster OCP. Ejemplo: -m https://master.novaltepre.corp:8443.";
    exit 1;
  fi

  oc logout

  oc login --token $TOKEN --server $MASTER

  if [ -z "$PROJECT" ];
  then
    eerror "Debes indicar el id de proyecto OCP donde está la app o servicio. Ejemplo: --project-id dev-account.";
    exit 1;
  fi

  if [ -z "$(oc projects -q | grep "^$PROJECT$")" ];
  then
    eerror "Projecto con ID $PROJECT no existe.";
    exit 1;
  fi

  oc project $PROJECT

  if [ -z "$NAME" ];
  then
    eerror "Debes indicar el nombre del app o servicio a exponer. Ejemplo: -n svr281723.";
    exit 1;
  fi

  if [ -z "$HOSTNAME" ];
  then
    eerror "Debes indicar la URL de exposición del app o servicio a exponer. Ejemplo: -h svr281723.";
    exit 1;
  fi

  CODE="oc expose svc $NAME --hostname=$HOSTNAME -l=\"${LABEL:1}\""

  if [ -n "$PORT" ]
  then
    CODE="$CODE --port=$PORT"
  fi

  if [ -n "$CONTEXTO" ]
  then
    CODE="$CODE --path=$CONTEXTO"
  fi

  if [ -n "$EXPOSENAME" ];
  then
    CODE="$CODE --name=$EXPOSENAME"
  fi

  edebug $CODE
  eval $CODE

  exit 0;
fi

if [ "${1}" = "add-limits" ];
then
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -t|--token)
    TOKEN="$2"
    shift # past argument
    shift # past value
    ;;
    -m|--master)
    MASTER="$2"
    shift # past argument
    shift # past value
    ;;
    --project-id)
    PROJECT="$2"
    shift # past argument
    shift # past value
    ;;
    --cpu)
    CPULIMIT="$2"
    shift # past argument
    shift # past value
    ;;
    --memory)
    MEMLIMIT="$2"
    shift # past argument
    shift # past value
    ;;
    -n|--name)
    NAME="$2"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters
  
  if [ -z "$TOKEN" ];
  then
    eerror "Debes indicar el token de acceso al cluster OCP. Ejemplo: -t 23nDSda231.";
    exit 1;
  fi

  if [ -z "$MASTER" ];
  then
    eerror "Debes indicar la URI de acceso al cluster OCP. Ejemplo: -m https://master.novaltepre.corp:8443.";
    exit 1;
  fi

  oc logout

  oc login --token $TOKEN --server $MASTER

  if [ -z "$PROJECT" ];
  then
    eerror "Debes indicar el id de proyecto OCP donde está la app o servicio. Ejemplo: --project-id dev-account.";
    exit 1;
  fi

  if [ -z "$(oc projects -q | grep "^$PROJECT$")" ];
  then
    eerror "Projecto con ID $PROJECT no existe.";
    exit 1;
  fi

  oc project $PROJECT

  if [ -z "$NAME" ];
  then
    eerror "Debes indicar el nombre de la configuración de despliegue. Ejemplo: -n svr281723.";
    exit 1;
  fi

  if [ -z "$CPULIMIT" -a -z "$MEMORYLIMIT" ];
  then
    eerror "Debes indicar al menos un limite, ya sea de CPU o de memoria. Ejemplo: --cpu 200m o --memory 400Mi.";
    exit 1;
  fi

  LIMITS=""

  if [ -n "$CPULIMIT" ]
  then
    LIMITS="$LIMITS,cpu=$CPULIMIT"
  fi

  if [ -n "$MEMLIMIT" ]
  then
    LIMITS="$LIMITS,memory=$MEMLIMIT"
  fi

  CODE="oc set resources deploymentconfig $NAME --limits=${LIMITS:1}"

  edebug $CODE
  eval $CODE

  exit 0;
fi

if [ "${1}" = "autoscaler" ];
then
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -t|--token)
    TOKEN="$2"
    shift # past argument
    shift # past value
    ;;
    -m|--master)
    MASTER="$2"
    shift # past argument
    shift # past value
    ;;
    --project-id)
    PROJECT="$2"
    shift # past argument
    shift # past value
    ;;
    --min)
    MINIMO="$2"
    shift # past argument
    shift # past value
    ;;
    --max)
    MAXIMO="$2"
    shift # past argument
    shift # past value
    ;;
    --porcentaje)
    PERCENT="$2"
    shift # past argument
    shift # past value
    ;;
    -n|--name)
    NAME="$2"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters
  
  if [ -z "$TOKEN" ];
  then
    eerror "Debes indicar el token de acceso al cluster OCP. Ejemplo: -t 23nDSda231.";
    exit 1;
  fi

  if [ -z "$MASTER" ];
  then
    eerror "Debes indicar la URI de acceso al cluster OCP. Ejemplo: -m https://master.novaltepre.corp:8443.";
    exit 1;
  fi

  oc logout

  oc login --token $TOKEN --server $MASTER

  if [ -z "$PROJECT" ];
  then
    eerror "Debes indicar el id de proyecto OCP donde está la app o servicio. Ejemplo: --project-id dev-account.";
    exit 1;
  fi

  if [ -z "$(oc projects -q | grep "^$PROJECT$")" ];
  then
    eerror "Projecto con ID $PROJECT no existe.";
    exit 1;
  fi

  oc project $PROJECT

  if [ -z "$NAME" ];
  then
    eerror "Debes indicar el nombre de la configuración de despliegue. Ejemplo: -n svr281723.";
    exit 1;
  fi

  if [ -z "$MINIMO" ];
  then
    eerror "Debes indicar el mínimo de replicas para el servicio o aplicación. Ejemplo: --min 2.";
    exit 1;
  fi

  if [ -z "$MAXIMO" ];
  then
    eerror "Debes indicar el mínimo de replicas para el servicio o aplicación. Ejemplo: --max 5.";
    exit 1;
  fi

  if [ -z "$PERCENT" ];
  then
    eerror "Debes indicar el porcentaje de consumo de CPU que desencadena las replicas del servicio o aplicación. Ejemplo: --porcentaje 85.";
    exit 1;
  fi

  if [ "$(oc get hpa | grep  $NAME | awk '{print $1}')" = "$NAME" ]
  then
    CODE="oc autoscale dc $NAME --min=$MINIMO --max=$MAXIMO --cpu-percent=$PERCENT --name=$NAME"
  else
    CODE="oc patch hpa $NAME -p '{ \"spec\": { \"minReplicas\": $MIN, \"maxReplicas\": $MAX, \"targetCPUUtilizationPercentage\": $PERCENT }}'"
  fi

  edebug $CODE
  eval $CODE

  exit 0;
fi

if [ "${1}" = "add-settings" ];
then
  POSITIONAL=()
  while [[ $# -gt 0 ]]
  do
  key="$1"
  case $key in
    -t|--token)
    TOKEN="$2"
    shift # past argument
    shift # past value
    ;;
    -m|--master)
    MASTER="$2"
    shift # past argument
    shift # past value
    ;;
    --project-id)
    PROJECT="$2"
    shift # past argument
    shift # past value
    ;;
    --from-file)
    FILEPROPERTIES="$2"
    shift # past argument
    shift # past value
    ;;
    --content)
    LITERAL="$2"
    shift # past argument
    shift # past value
    ;;
    --mount-path)
    MOUNTPATH="$2"
    shift # past argument
    shift # past value
    ;;
    -n|--name)
    NAME="$2"
    shift # past argument
    shift # past value
    ;;
    --default-format)
    DEFAULTFORMAT="true"
    shift # past argument
    shift # past value
    ;;
    --verbosity)
    verbosity=$2
    shift
    shift
    ;;
    *)
    POSITIONAL+=("$1") # save it in an array for later
    shift # past argument
    ;;
  esac
  done
  set -- "${POSITIONAL[@]}" # restore positional parameters

  if [ -z "$TOKEN" ];
  then
    eerror "Debes indicar el token de acceso al cluster OCP. Ejemplo: -t 23nDSda231.";
    exit 1;
  fi

  if [ -z "$MASTER" ];
  then
    eerror "Debes indicar la URI de acceso al cluster OCP. Ejemplo: -m https://master.novaltepre.corp:8443.";
    exit 1;
  fi

  oc logout

  oc login --token $TOKEN --server $MASTER

  if [ -z "$PROJECT" ];
  then
    eerror "Debes indicar el id de proyecto OCP donde está la app o servicio. Ejemplo: --project-id dev-account.";
    exit 1;
  fi

  if [ -z "$(oc projects -q | grep "^$PROJECT$")" ];
  then
    eerror "Projecto con ID $PROJECT no existe.";
    exit 1;
  fi

  oc project $PROJECT

  if [ -z "$NAME" ];
  then
    eerror "Debes indicar el nombre del app o servicio a exponer. Ejemplo: -n svr281723.";
    exit 1;
  fi

  if [ "$(oc get configmap | grep -c $NAME | awk '{print $1}')" = "$NAME" ]
  then
    enotify "El archivo de propriedades ya esta fue creado"
    
    CODE="oc volume dc $NAME --overwrite --add -t configmap --mount-path $MOUNTPATH --name=properties-volume --configmap-name=$NAME-properties"
    
    edebug $CODE
    eval $CODE

    exit 0;
  fi

  if [ -z "$MOUNTPATH" ];
  then
    eerror "Debes indicar la ruta de montaje del archivo de propiedades. Ejemplo: --mount-path /path/for/file.";
    exit 1;
  fi

  if [ -z "$FILEPROPERTIES" -a -z "$LITERAL" ];
  then
    eerror "Debes indicar un archivo de propiedades o el contenido para este. Ejemplo: --from-file /path/of/file.properties o --content=\"cl.wom.uri=wom.cl\r\ncl.wom.user=pepito\".";
    exit 1;
  fi

  if [ -n "$FILEPROPERTIES" -a -n "$LITERAL" ];
  then
    eerror "Debes indicar un archivo de propiedades o el contenido para este. Ejemplo: --from-file /path/of/file.properties o --content=\"cl.wom.uri=wom.cl\r\ncl.wom.user=pepito\".";
    exit 1;
  fi

  if [ "$DEFAULTFORMAT" = "true" ]
  then
    enotify "Se utilizará el formato por defecto, se considera que tienes declarado las siguientes variables declaradas en el entorno: LOGLEVEL, USER_RK, PASS_RK, BACK_HOST, BACK_PORT, BACK_URI, SRV_ID, OPERATION_ID, SRV_HOST, SRV_PORT."
    CODE="oc create -f - <<API
    apiVersion: v1
    metadata:
      name: $NAME-properties
    data:
      $NAME.properties: |-
        logging.level.= ${LOGLEVEL}
        camel.timeout.redelivery-delay=3000
        camel.timeout.max-delivery=4
        camel.soap.basicauth.user=${USER_RK}
        camel.soap.basicauth.pass=${PASS_RK}
        soap.service.host=${BACK_HOST}
        soap.service.port= ${BACK_PORT}
        soap.service.resource=${BACK_URI}
        orquestacion.service=${SRV_ID}
        orquestacion.operacion=${OPERATION_ID}
        orquestacion.endpoint=${SRV_HOST}
        orquestacion.puerto=${SRV_PORT}
    kind: ConfigMap
    API"
  else
    if [ -n "$FILEPROPERTIES" ]
    then
      CODE="oc create configmap $NAME-properties --from-file=$NAME.properties=$FILEPROPERTIES"
    else
      CODE="oc create configmap $NAME-properties --from-literal=$NAME.properties=$LITERAL"
    fi
  fi
  

  edebug $CODE
  eval $CODE

  CODE="oc volume dc $NAME --overwrite --add -t configmap --mount-path $MOUNTPATH --name=properties-volume --configmap-name=$NAME-properties"
    
  edebug $CODE
  eval $CODE

  exit 0;
fi

enotify "Debe indicar el proceso que desea hacer: compile, build, push, deploy, expose, add-limits, autoscaler o add-settings";
