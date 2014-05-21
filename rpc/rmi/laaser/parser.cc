/*
 * Laas Compiler.
 * Copyright (C) 2014 TasukuTAKAHASHI All Rights Reserved.
 * This file is a part of Laas.
 *
 * Laas is , and open source software.
 * you can redistribute it and/or modify it.
 * Use Laas !! */

#include "parser.h"

void 
init_parser()
{
	_pack_list["org.laas.HashMap"] = true;
	_pack_list["org.opencv.core.Core"] = true;
	
	return;
}

/*
 * It's open first error log file.
 */
void
read_error(string filename)
{
	string a_line;  
	smatch a_match;
	ifstream a_file(filename);  
	regex re_pack_error(".+:[0-9]+: *error: *package .+ does not exist");
	regex re_name_error(".+:[0-9]+: *error: *cannot find symbol");
	
	while(getline(a_file,a_line)) 
	{  
		if(regex_search(a_line, a_match, re_pack_error)) 
		{
			string lines[PACK_ERROR_LINE_NUM];
			
			for (int i=0; i<PACK_ERROR_LINE_NUM; i++) {
				getline(a_file,lines[i]);
			}
		
			rep_split(a_token,lines[0]," ;") {
				cout << a_token << endl;
			}
		}
		else if(regex_search(a_line, a_match, re_name_error)) 
		{
			/*
			string lines[NAME_ERROR_LINE_NUM];

			for (int i=0; i<NAME_ERROR_LINE_NUM; i++)
			{
				getline(a_file,lines[i]);
				cout << i << ": " << lines[i] << endl;
			}
			*/
		}
	}

	return;
}

