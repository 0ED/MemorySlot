/*
 * Laas Compiler.
 * Copyright (C) 2014 TasukuTAKAHASHI All Rights Reserved.
 * This file is a part of Laas.
 *
 * Laas is , and open source software.
 * you can redistribute it and/or modify it.
 * Impress Paladin !! */

#ifndef __INCLUDE_LAAS_H__
#define __INCLUDE_LAAS_H__

#include <iostream>
#include <cstring>
#include <cstdlib>
#include <regex>
#define rep_split(tok,a_str,re) for(char *tok = strtok((char *)a_str.c_str(),re); tok != NULL; tok = strtok(NULL,re))

using namespace std;

int main(int,char*[]);
void print_log_of_error(char*[]);
void help(void);

#endif /* !__INCLUDE_LAAS_H__ */
