/*
 * ウィンドウに四角形を描画する簡単な Xlib アプリケーション
 *
 * gcc wiki.c -I/usr/X11R6/include -L/usr/X11R6/lib -lX11
 */

#include <X11/Xlib.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
	Display *d;
	int s;
	Window w;
	XEvent e;

	/* サーバとのコネクションを開く */
	d=XOpenDisplay(NULL);
	if(d==NULL) {
		printf("Cannot open display\n");
		exit(1);
	}
	s=DefaultScreen(d);

	/* ウィンドウ生成 */
	w=XCreateSimpleWindow(d, RootWindow(d, s), 10, 10, 100, 100, 1,
			BlackPixel(d, s), WhitePixel(d, s));

	/* 受け付けるイベントの種類を選択 */
	XSelectInput(d, w, ExposureMask | KeyPressMask);

	/* ウィンドウを可視化 */
	XMapWindow(d, w);

	while(1) {
		XNextEvent(d, &e);
		/* ウィンドウの描画と再描画 */
		if(e.type==Expose) {
			XFillRectangle(d, w, DefaultGC(d, s), 20, 20, 10, 10);
			XDrawString(d, w, DefaultGC(d, s), 50, 50, "Hello, World!",strlen("Hello, World!"));
		}
		if(e.type==KeyPress)
			break;
	}
	XCloseDisplay(d);

	return 0;
}
