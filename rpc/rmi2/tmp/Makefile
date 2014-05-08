all:
	javac *.java
	rmic helloObj

rmi:
	rmiregistry

server:
	java -D -Djava.security.policy=a.policy -Djava.rmi.server.codebase="http://localhost:8080" helloServer

client:
	java -Djava.security.policy=a.policy helloClient

