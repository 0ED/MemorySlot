DOMAIN = localhost
PORT = 1099
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
	rmic ${ROBJ}

server:	
	rmiregistry ${PORT} & java -Djava.rmi.server.codebase=file:/// ${SERVER}
client:
	java rmi://${DOMAIN}:${PORT}/${REGISTRY_NAME}
clean:
	${RM} ${CLASSES}
	${RM} ${ROBJ}_Stub.java
	${RM} ${ROBJ}_Stub.class

