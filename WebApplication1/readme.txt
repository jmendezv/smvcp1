When the server can not be launched because a port is being used it is necessary to kill the service listening on that port.

sudo netstat -lnp | egrep 8080

kill -9 <port>
