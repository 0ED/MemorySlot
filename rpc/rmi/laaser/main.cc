#include "main.h"

/*
 * Main of Laas Compliler.
 */
int 
main(int argument_count, char* argument_values[])
{

	if (argument_count < 2) {
		return EXIT_FAILURE;
	}
	else {
		string in_file = "error.log";

		init_compiler();
		string compiler_name = get_compiler_name(argument_count, argument_values);
		if (compiler_name.empty()) return EXIT_FAILURE;
		
		compile_and_redirect_to(compiler_name, in_file, argument_count, argument_values);
		init_parser();
		read_error(in_file);
	}

	return EXIT_SUCCESS;
}

