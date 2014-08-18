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

static unordered_map<string,bool> _pack_list;
static unordered_map<string,bool> _pack_check_list;

void init_parser(void);
void read_error(string);
void read_pack_error(ifstream&);
void read_name_error(ifstream&);

#endif /* !__INCLUDE_PARSER_H__ */
