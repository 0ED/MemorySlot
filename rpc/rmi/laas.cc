/*
 * Laas Compiler.
 * Copyright (C) 2014 TasukuTAKAHASHI All Rights Reserved.
 * This file is a part of Laas.
 *
 * Laas is , and open source software.
 * you can redistribute it and/or modify it.
 * Use Laas !! */

#include "laas.h"

/*
 * Main of Laas Compliler.
 */
int 
main(int argument_count, char* argument_values[])
{
	const char* filename = "error.log";
	
	if (argument_count < 2)
	{
		help();
	}
	else
	{
		redirect_to(filename, argument_count, argument_values);
		//open(filename);
	}

	return EXIT_SUCCESS;
}

/*
 * It's open first error log file.
 */
void
open(const char* filename)
{
	int linecounter = 0;
	string line = NULL;  
	ifstream a_file("error.log");  
	while(getline(a_file,line))  
	{  
		cout << line;  
		linecounter++;
	}

	return;
}

/*
 *  
 */
void
redirect_to(const char* filename, int argument_count, char* argument_values[])
{
	string a_command;
	a_command = "javac -J-Dfile.encoding=UTF8 -J-Duser.language=en ";
	for (int i=1; i < argument_count; i++)
	{
		a_command += string(argument_values[i]) + " ";
	}
	a_command += "2> ";
	a_command += filename;
	system(a_command.c_str());

	return;
}

/*
 *
 */
void 
help(void) 
{
	return;
}


