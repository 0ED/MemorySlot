#
# Copyright (c) TasukuTAKAHASHI All rights reserved.  
#
# 	make
#	make test
#

CURDIR		= JoinTabler
PACKAGE 	= joinTabler
SOURCES		= \
	$(PACKAGE)/JoinTabler.java
OBJS		= $(SOURCES:%.java=%.class)

all:
	javac -cp .:lib/jxl.jar $(SOURCES)
	jar cvfm $(PACKAGE).jar $(PACKAGE).mf $(OBJS)
test:
	java -jar $(PACKAGE).jar
open: 
	open $(SOURCES)
clean:
	$(RM) \
	$(PACKAGE).jar \
	$(OBJS)
tar: clean
	cd ..; tar cvzf $(CURDIR).tgz $(CURDIR)

