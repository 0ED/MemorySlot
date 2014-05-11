#include <iostream>
#include <cstring>
#include <cstdlib>
#include <regex>
#define rep_split(tok,a_str,re) for(char *tok = strtok((char *)a_str.c_str(),re); tok != NULL; tok = strtok(NULL,re))

using namespace std;

int main(int,char*[]);
void print_log_of_error(char*[]);
void help(void);
