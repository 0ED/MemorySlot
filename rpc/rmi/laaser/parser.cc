/*
 * Laas Compiler.
 * Copyright (C) 2014 TasukuTAKAHASHI All Rights Reserved.
 * This file is a part of Laas.
 *
 * Laas is , and open source software.
 * you can redistribute it and/or modify it.
 * Use Laas !! */

#include "parser.h"

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
help(void) 
{
	return;
}


