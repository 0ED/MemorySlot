/*
 * Laas Compiler.
 * Copyright (C) 2014 TasukuTAKAHASHI All Rights Reserved.
 * This file is a part of Laas.
 *
 * Laas is , and open source software.
 * you can redistribute it and/or modify it.
 * Use Laas !! */

#ifndef __INCLUDE_COMPILER_H__
#define __INCLUDE_COMPILER_H__

#include <iostream>
#include <cstring>
#include <cstdlib>
#include <regex>
#include <unordered_map>

using namespace std;

static unordered_map<string,string> _compiler_command_map;

void init_compiler(void);
string get_compiler_name(int, char*[]);
void compile_and_redirect_to(string, string, int, char*[]);

#endif /* !__INCLUDE_COMPILER_H__ */
