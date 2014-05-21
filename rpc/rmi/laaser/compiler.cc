#include "compiler.h"

/*
 * It initializes compiler flag map.
 */
void 
init_compiler()
{
	_compiler_command_map.insert(make_pair(".c", "cc ")); 
	_compiler_command_map.insert(make_pair(".cc", "c++ ")); 
	_compiler_command_map.insert(make_pair(".cpp", "c++ ")); 
	_compiler_command_map.insert(make_pair(".cxx", "c++ ")); 
	_compiler_command_map.insert(make_pair(".java", "javac -J-Dfile.encoding=UTF8 -J-Duser.language=en ")); 
	_compiler_command_map.insert(make_pair(".pl", "perl ")); 
	_compiler_command_map.insert(make_pair(".py", "python ")); 
	_compiler_command_map.insert(make_pair(".rb", "ruby ")); 

	return;
}

/*
 * It gets a filename extension of compiler from argument_values.
 * If it unsupported, return ""
 */
string 
get_compiler_name(int argument_count, char* argument_values[]) 
{
	cmatch a_match;
	regex re_file("\\.(c|cc|cpp|cxx|java|py|rb|pl)");

	for(int i=1; i<argument_count; i++)
	{
		if(regex_search(argument_values[i], a_match, re_file)) 
		{
			return string(a_match[0]);
		}
	}
	return "";
}

/*
 * It compiles Program files, and redirect to error log file.
 */
void
compile_and_redirect_to(
		string compiler_name,
		string filename,
		int argument_count,
		char* argument_values[])
{
	string a_command;
	a_command = _compiler_command_map[compiler_name];
	for (int i=1; i < argument_count; i++) 
	{
		a_command += string(argument_values[i]) + " ";
	}
	a_command += "2> ";
	a_command += filename;
	system(a_command.c_str());

	return;
}

