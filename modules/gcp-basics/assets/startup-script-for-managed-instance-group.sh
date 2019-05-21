#! /bin/bash

apt-get update
apt-get install -y apache2
MY_NAME=$(curl  -H 'Metadata-Flavor: Google' http://metadata.google.internal/computeMetadata/v1/instance/name)
cat <<EOF > /var/www/html/index.html
<html><body><h1>Hello from instance ${MY_NAME}</h1>
<p>This page was created from a simple startup script!</p>
</body></html>
EOF