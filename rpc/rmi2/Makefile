all:
	javac *.java
server:
	java -Djava.security.policy=a.policy -Djava.rmi.server.codebase=file:/// Server

client:
	java Client rmi://localhost:8000/rmisample

clean:
	${RM} *.class
