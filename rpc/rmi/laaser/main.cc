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
		string error_log = "error.log";

		init_compiler();
		string compiler_name = get_compiler_name(argument_count, argument_values);
		if (compiler_name.empty()) return EXIT_FAILURE;
		
		compile_and_redirect_to(compiler_name, error_log, argument_count, argument_values);
		init_translater();
		translate_to_meta(error_log);
	}

	return EXIT_SUCCESS;
}

