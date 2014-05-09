DOMAIN = localhost
PORT = 8000 
REGISTRY_NAME = rmisample
CLIENT = Client
SERVER = Server
ROBJ = RMISample
CLASSES = \
	${CLIENT}.class \
	${SERVER}.class \
	${ROBJ}.class \
	${ROBJ}Interface.class
SRCS = ${CLASSES:%.class=%.java}

all:
	javac ${SRCS}
	rmic -keep ${ROBJ} -v1.2 
rmi:	
	rmiregistry ${PORT} 
server:
	echo java -Djava.rmi.server.codebase=file:/// ${SERVER} 
	# rmi://${DOMAIN}:${PORT}/rmisample
client:
	java rmi://${DOMAIN}:${PORT}/${REGISTRY_NAME}
clean:
	${RM} ${CLASSES}
	${RM} ${ROBJ}_Stub.java
	${RM} ${ROBJ}_Stub.class

