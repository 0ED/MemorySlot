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
			read_pack_error(a_file);
		}
		else if(regex_search(a_line, a_match, re_name_error)) 
		{
			read_name_error(a_file);
		}
	}

	return;
}

void 
read_pack_error(ifstream &a_file) 
{
	vector<char*> tokens;
	string lines[PACK_ERROR_LINE_NUM];
	
	for (int i=0; i<PACK_ERROR_LINE_NUM; i++) {
		getline(a_file,lines[i]);
	}
	rep_split(a_token,lines[0]," \t;") {
		tokens.push_back(a_token);
	}
	
	if (strcmp(tokens.front(),"import") == 0) 
	{
		/*
		 * ex: 
		 *   import java.io.Hoge;
		 *   using Hoge
		 */
		string lib_path = string(tokens[1]);
		tokens.clear();
		if (_pack_list[lib_path]) 
		{
			rep_split(a_token,lib_path,"\\.")
			{
				tokens.push_back(a_token);
			}
			_pack_check_list[tokens.back()] = true;
			cout << tokens.back() << endl;
		}
	}
	else {
		/*
		 * ex:
		 *   using java.io.Hoge;
		 */
		
	}
}

void 
read_name_error(ifstream &a_file)
{
	// ex: new Hoge()
	string lines[NAME_ERROR_LINE_NUM];

	for (int i=0; i<NAME_ERROR_LINE_NUM; i++) {
		getline(a_file,lines[i]);
	}

	vector<char*> pack_classes;
	vector<char*> user_classes;
	rep_split(a_token,lines[3]," \t:") {
		pack_classes.push_back(a_token);	
	}
	rep_split(a_token,lines[4]," \t:") {
		user_classes.push_back(a_token);	
	}

	if (strcmp(pack_classes[1],"class") == 0) 
	{
		if(_pack_check_list[pack_classes[2]]) {
			
		}
	}

	return;
}
