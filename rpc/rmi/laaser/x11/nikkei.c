/*
 * gcc nikkei.c -I/usr/X11R6/include -L/usr/X11R6/lib -lXaw -lXmu -lXt -lX11
 */
#include <X11/Intrinsic.h> 
#include <X11/StringDefs.h>
#include <X11/Xaw/Label.h>

int
main(int argc, char **argv)
{
	XtAppContext app_context;
	Widget topLevel, hello;

	topLevel = XtVaAppInitialize(
			&app_context, 
			"Hello",  
			NULL, 0, 
			&argc, argv,      
			NULL,
			NULL  
			);
	hello = XtVaCreateManagedWidget(
			"label", 
			labelWidgetClass,
			topLevel,
			XtNwidth, 160, XtNheight, 80,
			XtNlabel, "Hello World",
			NULL
			);
	XtRealizeWidget(topLevel); 
	XtAppMainLoop(app_context);
	return 0;
}
