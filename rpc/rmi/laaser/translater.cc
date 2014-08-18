/*
 * Laas Compiler.
 * Copyright (C) 2014 TasukuTAKAHASHI All Rights Reserved.
 * This file is a part of Laas.
 *
 * Laas is , and open source software.
 * you can redistribute it and/or modify it.
 * Use Laas !! */

#include "translater.h"


void
convet_laas_name(string src_path)
{
	string laas_src_path="";
	rep_split(a_token,src_path,"/")
	{
	}

	return laas_src_path;
}

UserCode
get_user_code(string src_path)
{
	if ( !_userCodes[src_path].file.is_open() ) 
	{
		_userCodes[src_path].file.open(src_path);
		_userCodes[src_path].file.open(convet_laas_name(src_path));
		_userCodes[src_path].pack_list["org.laas.HashMap"] = true;
		_userCodes[src_path].pack_list["org.opencv.core.Core"] = true;
	}
	return _userCodes[src_path];
}

/*
 * It's open first error log file.
 */
void
translate_to_meta(string filename)
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
			string src_path = string(strtok((char *)a_line.c_str()," \t:"));
			read_pack_error(a_file, src_path);
		}
		else if(regex_search(a_line, a_match, re_name_error)) 
		{
			string src_path = string(strtok((char *)a_line.c_str()," \t:"));
			read_name_error(a_file, src_path);
		}
	}

	return;
}

/*
 *
 * EX:
 *		import org.laas.HashMap;
 *		^
 */
void
read_pack_error(ifstream &a_file, string src_path)
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
		if (_userCodes[src_path].pack_list[lib_path])
		{
			rep_split(a_token,lib_path,"\\.")
			{
				tokens.push_back(a_token);
			}
			_userCodes[src_path].pack_use_list[tokens.back()] = true;
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

/*
 *
 *
 *	EX:
 *		Map<String,Integer> aHashMap = new HashMap<String,Integer>();;
 *		^
 *		symbol:   class HashMap
 *		location: class Client
 */
void
read_name_error(ifstream &a_file, string src_path)
{
	string lines[NAME_ERROR_LINE_NUM];

	for (int i=0; i<NAME_ERROR_LINE_NUM; i++) {
		getline(a_file,lines[i]);
	}

	vector<char*> pack_classes;
	vector<char*> user_classes;
	rep_split(a_token,lines[3]," \t:") {
		pack_classes.push_back(a_token); // symbol:		class HashMap
	}
	rep_split(a_token,lines[4]," \t:") {
		user_classes.push_back(a_token); // location:	class Client
	}

	if (strcmp(pack_classes[1],"class") == 0) 
	{
		if(_userCodes[src_path].pack_use_list[pack_classes[2]])
		{
			//importしていたら
			
		}
	}

	return;
}
