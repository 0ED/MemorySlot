package HelloMod;

/**
* HelloMod/HelloHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Hello.idlから
* 2014年5月9日 23時42分16秒 JST
*/

public final class HelloHolder implements org.omg.CORBA.portable.Streamable
{
  public HelloMod.Hello value = null;

  public HelloHolder ()
  {
  }

  public HelloHolder (HelloMod.Hello initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = HelloMod.HelloHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    HelloMod.HelloHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return HelloMod.HelloHelper.type ();
  }

}
