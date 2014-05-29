// x1.c		Xlibを使ったhello 
// gcc nikkei.c -I/usr/X11R6/include -L/usr/X11R6/lib -lXaw -lXmu -lXt -lX11
#include <X11/Xlib.h>
#include <X11/Xutil.h> 
#include <stdio.h> 
#include <string.h> 

char hello[] = {"hello X"}; 

int
main(int argc, char **argv) 
{ 
	Display *mydisplay; 
	Window mywindow; 
	GC mygc; 
	XEvent myevent; 
	XSizeHints myhint; 
	int myscreen; 
	unsigned long myforeground, mybackground; 
	int n; 
		// Ｘサーバに接続する
		mydisplay = XOpenDisplay(""); 
	myscreen = DefaultScreen( mydisplay ); 
	// Ｘサーバで使用されている基本色を得る 
	mybackground = WhitePixel( mydisplay, myscreen ); 
	myforeground = BlackPixel( mydisplay, myscreen ); 
	// ウィンドウの位置、大きさを指定 
	myhint.x = 200; myhint.y = 200; 
	myhint.width = 300; myhint.height = 200; 
	myhint.flags = PPosition | PSize; 
	// ウィンドウを生成 
	mywindow = XCreateSimpleWindow ( mydisplay, 
				DefaultRootWindow( mydisplay ), 
				myhint.x, myhint.y, myhint.width, myhint.height, 
				5, myforeground, mybackground); 
	// 生成したウィンドウの情報をシステムに知らせる 
	XSetStandardProperties( mydisplay, mywindow, hello, hello, 
				None, argv, argc, &myhint ); 
	// グラフィックを描くための準備	 
	mygc = XCreateGC( mydisplay, mywindow, 0, 0); 
	XSetBackground( mydisplay, mygc, mybackground ); 
	XSetForeground( mydisplay, mygc, myforeground ); 
	// 受け取るイベントの選択 
	XSelectInput( mydisplay, mywindow, 
				ButtonPressMask | ExposureMask ); 
	// ウィンドウを表示 
	XMapRaised( mydisplay, mywindow ); 
	// イベントループ 
	n = 0; 
	while ( n < 10 ){ 
		// イベントの取り出し 
		XNextEvent ( mydisplay, &myevent ); 
		switch ( myevent.type ) {
			// 再描画要求 
			case Expose: 
				if ( myevent.xexpose.count == 0 ){ 
					printf("repaint\n"); 
					XDrawImageString( 
					myevent.xexpose.display, 
					myevent.xexpose.window, 
					mygc,50,50,	 
					hello,strlen(hello)); 
				} 
				break; 
			// マウスのボタンが押された 
			case ButtonPress: 
				XBell( mydisplay, 0 ); 
				printf( "button down %d \n", n++ ); 
				break; 
		}	 
	} 
	XFreeGC(mydisplay, mygc); // コンテキスト解放 
	XDestroyWindow(mydisplay, mywindow); // ウィンドウの破棄 
	XCloseDisplay(mydisplay); // 接続の解除 
	return 0; 
} 

