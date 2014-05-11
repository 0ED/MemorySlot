ME = Hitler
ENEMY = SampleRobo
SOURCES = Robos
CLASSES = .
all1:
	javac -cp robobattler3.jar:. ${SOURCES}/${ME}.java
all2:
	javac -cp robobattler3.jar:. ${SOURCES}/${ENEMY}.java
test1:	
	java -cp robobattler3.jar:. robobattler3/RoboBattler3 ${ME} ${ENEMY}
test2:
	java -cp robobattler3.jar:. robobattler3/RoboBattler3 ${ENEMY} ${ME} 
open:
	open ${SOURCES}/${ME}.java 
clean:
	${RM} ${CLASSES}/*.class
