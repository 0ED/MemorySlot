TARGET 		= laasc
SAMPLE_CODE	= ../jp/laas/client/Client.java 
CXX			= c++
OBJS		= \
		main.o \
		compiler.o \
		translater.o
HEADS		= ${OBJS:%.o=%.h}
SRCS		= ${OBJS:%.o=%.cc}
LOG_FILE	= error.log
CFLAGS = -W -O2

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CXX) ${CFLAGS} -o $@ $(OBJS)

test:
	./${TARGET} ${SAMPLE_CODE}
install:
	sudo mv ${TARGET} /usr/bin/ 
x:
	open -a xcode ${SRCS} ${HEADS}
vim:
	vim ${SRCS} ${HEADS}
clean:
	$(RM) $(TARGET) $(LOG_FILE) $(OBJS)

