TARGET 		= laas
SAMPLE_CODE	= Client.java 
CXX			= c++
OBJS		= \
	laas.o
HEADERS		= ${OBJS:%.o=%.h}
SOURCES		= ${OBJS:%.o=%.cc}
LOG_FILE	= error.log

all:
	${CXX} -o ${TARGET} ${SOURCES} 
	@#clang++ -std=c++11 -o ${TARGET} ${SOURCES}
test:
	./${TARGET} ${SAMPLE_CODE}
x:
	open -a xcode ${SOURCES} ${HEADERS}
vim:
	vim ${SOURCES} ${HEADERS}
clean:
	${RM} ${TARGET} ${LOG_FILE}

