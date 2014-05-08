package HelloMod;


/**
* HelloMod/HelloHelper.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Hello.idlから
* 2014年5月6日 23時13分32秒 JST
*/

abstract public class HelloHelper
{
  private static String  _id = "IDL:HelloMod/Hello:1.0";

  public static void insert (org.omg.CORBA.Any a, HelloMod.Hello that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static HelloMod.Hello extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (HelloMod.HelloHelper.id (), "Hello");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static HelloMod.Hello read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_HelloStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, HelloMod.Hello value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static HelloMod.Hello narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HelloMod.Hello)
      return (HelloMod.Hello)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HelloMod._HelloStub stub = new HelloMod._HelloStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static HelloMod.Hello unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof HelloMod.Hello)
      return (HelloMod.Hello)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      HelloMod._HelloStub stub = new HelloMod._HelloStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}