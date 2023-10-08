echo "Works!! $*"
BASEDIR=$(dirname "$0")
java -jar "${BASEDIR}"/build/libs/app-standalone.jar $*