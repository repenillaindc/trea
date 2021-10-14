#!/bin/bash
queueManagerHostname_1=$5
destinationQueueManagerName_1=$4
listenerPortNumber_1=$6
channelName_1=$7
versionAce=$8

echo "Seleecion de MQ"
MQ=$1
pod=$2
work_dir=$3
echo "copiando archivo"
echo "pod"
echo $pod
echo $work_dir
cp /app/bbva/esb/proyecto/$work_dir/properties/Constantes_$work_dir.properties /app/bbva/esb/proyecto/$work_dir/properties/$pod.properties
archivo=/app/bbva/esb/proyecto/$work_dir/properties/$pod.properties
echo $archivo
echo "Configuracion  MQ1"
sed -i 's/%IP_POD%/'"$pod"'/g'  $archivo
sed -i 's/%queueManagerHostname%/'"$queueManagerHostname_1"'/g' $archivo
sed -i 's/%destinationQueueManagerName%/'"$destinationQueueManagerName_1"'/g' $archivo
sed -i 's/%listenerPortNumber%/'"$listenerPortNumber_1"'/g'  $archivo
sed -i 's/%channelName%/'"$channelName_1"'/g'  $archivo
sed -i 's/%Qmgr%/'"$destinationQueueManagerName_1"'/g' /opt/IBM/$versionAce/server/workdir/$work_dir/server.conf.yaml
