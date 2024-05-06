# Compiler
JAVAC = javac

# Flags
JFLAGS = -d bin

# Source files
SRCS = $(wildcard *.java)

# Classes
CLASSES = $(SRCS:.java=.class)

# Main class
MAIN = Main

# Default target
all: $(CLASSES)

# Compile Java source files
%.class: %.java
	$(JAVAC) $(JFLAGS) $<

# Run the program
run:
	java -cp bin $(MAIN)

# Clean generated files
clean:
	rm -rf bin/*.class

.PHONY: all run clean
