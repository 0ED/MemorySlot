DOMAIN 		= 133.101.48.182
PORT 		= 8000
REGISTRY_NAME = rmisample
CURDIR 		= rmi
STUBDIR		= jp/laas
CLIENT		= ${STUBDIR}/client/LaasClient
SERVER		= ${STUBDIR}/server/Server
ROBJ		= LaasHashMapImpl
CLASSES 	= \
	${CLIENT}.class \
	${SERVER}.class \
	${STUBDIR}/LaasHashMap.class \
	${STUBDIR}/LaasHashMapImpl.class
SRCS 		= ${CLASSES:%.class=%.java}
BACKUP 		= rmi_back_3.zip
RMI_HOST	= rmi://$(DOMAIN):$(PORT)

all:
	javac ${SRCS}
	rmic -keep jp.laas.${ROBJ} -v1.2 
rmi:	
	rmiregistry ${PORT} 
server:
	java -Djava.rmi.server.codebase=file:///jp/laas/ $(SERVER) $(RMI_HOST)
client:
	java ${CLIENT} $(RMI_HOST)
clean:
	${RM} ${CLASSES}
	${RM} ${STUBDIR}/${ROBJ}_Stub.java
	${RM} ${STUBDIR}/${ROBJ}_Stub.class
zip: clean
	cd ..; zip -r ${BACKUP} ${CURDIR}
x:
	open -a xcode ${SRCS}
vim:
	vim ${SRCS}
