DOMAIN = localhost
PORT = 8000
REGISTRY_NAME = rmisample
CLIENT = RMISampleClient
SERVER = RMISampleServer
ROBJ = RMISampleImpl
CLASSES = \
	${CLIENT}.class \
	${SERVER}.class \
	RMISample.class \
	RMISampleImpl.class
SRCS = ${CLASSES:%.class=%.java}

all:
	javac ${SRCS}
	rmic -keep ${ROBJ} -v1.2 
rmi:	
	rmiregistry ${PORT} 
server:
	java -Djava.rmi.server.codebase=file:/// ${SERVER} 
client:
	java ${CLIENT} rmi://${DOMAIN}:${PORT}/${REGISTRY_NAME}
clean:
	${RM} ${CLASSES}
	${RM} ${ROBJ}_Stub.java
	${RM} ${ROBJ}_Stub.class

