SERVER = LaasServer
CLIENT = Client
MODULE = HelloMod
IDL = Hello.idl

all:
	idlj -fall ${IDL}
	javac ${MODULE}/*.java
	javac *.java

sys:
	orbd -ORBInitialPort 1050 -ORBInitialHost localhost
	
server:
	java ${SERVER} -ORBInitialPort 1050 -ORBInitialHost localhost

client:
	java ${CLIENT} -ORBInitialPort 1050 -ORBInitialHost localhost

clean:
	${RM} *.class
	${RM} -r ${MODULE}

