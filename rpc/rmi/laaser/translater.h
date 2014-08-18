/*
 * Laas Compiler.
 * Copyright (C) 2014 TasukuTAKAHASHI All Rights Reserved.
 * This file is a part of Laas.
 *
 * Laas is , and open source software.
 * you can redistribute it and/or modify it.
 * Use Laas !! */

#ifndef __INCLUDE_PARSER_H__
#define __INCLUDE_PARSER_H__

#include <iostream>
#include <fstream>
#include <sstream>
#include <cstring>
#include <cstdlib>
#include <regex>
#include <unordered_map>
#define rep_split(tok,a_str,re) for(char *tok = strtok((char *)a_str.c_str(),re); tok != NULL; tok = strtok(NULL,re))
#define PACK_ERROR_LINE_NUM 2
#define NAME_ERROR_LINE_NUM 4

using namespace std;


typedef struct {
	ifstream file;
	unordered_map<string,bool> pack_list;
	unordered_map<string,bool> pack_use_list;
} UserCode;

static unordered_map<string,UserCode> _userCodes;

string convet_laas_name(string);
UserCode get_user_code(string);
void read_error(string);
void read_pack_error(ifstream&);
void read_name_error(ifstream&);

#endif /* !__INCLUDE_PARSER_H__ */
