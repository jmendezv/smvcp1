When the server cannot be launched because its port is being used it is necessary to kill the service listening on that port.

sudo netstat -lnp | egrep 8080

kill -9 <port>

Security (TODO)

http://www.beingjavaguys.com/2014/08/spring-security-with-hibernate.html