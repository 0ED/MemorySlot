#include "laas.h"

/*
 * Main of Laas Compliler.
 */
int 
main(int argumentCount, char* argumentValues[]) 
{
	if (argumentCount < 2) 
	{
		help();
	}
	else
	{
		print_log_of_error(argumentValues);
	}

	return EXIT_SUCCESS;
}

/*
 * 
 */
void
print_log_of_error(char* argumentValues[]) 
{
	string a_command = "javac -J-Dfile.encoding=UTF8 -J-Duser.language=en ";
	a_command += argumentValues[1];
	a_command += " 2> ";
	a_command += "error.log";
	cout << a_command << endl;
	system(a_command.c_str());

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

