DOMAIN = localhost
PORT = 8000
REGISTRY_NAME = rmixxx
CLIENT = Client
SERVER = Server
ROBJ = RMISImpl
CLASSES = \
	${CLIENT}.class \
	${SERVER}.class \
	RMIS.class \
	RMISImpl.class
SRCS = ${CLASSES:%.class=%.java}

all:
	javac ${SRCS}
	rmic -keep ${ROBJ} -v1.2 
rmi:
	rmiregistry ${PORT} 
server:
	java -Djava.rmi.server.codebase=file:/// ${SERVER} 
	# rmi://${DOMAIN}:${PORT}/rmisample
client:
	java ${CLIENT} rmi://${DOMAIN}:${PORT}/${REGISTRY_NAME}
clean:
	${RM} ${CLASSES}
	${RM} ${ROBJ}_Stub.java
	${RM} ${ROBJ}_Stub.class

