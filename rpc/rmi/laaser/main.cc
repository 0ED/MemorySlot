#include "main.h"

/*
 * Main of Laas Compliler.
 */
int 
main(int argument_count, char* argument_values[])
{
	init_compiler();

	if (argument_count < 2) {
		return EXIT_FAILURE;
	}
	else {
		string in_file = "error.log";

		string compiler_name = get_compiler(argument_count, argument_values);
		if (compiler_name.empty()) return EXIT_FAILURE;
		
		cout << compiler_name << endl;
		compile_and_redirect_to(compiler_name, in_file, argument_count, argument_values);
		read_error(in_file);
	}

	return EXIT_SUCCESS;
}

