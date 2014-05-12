package schedule;


/**
* schedule/SchedulerHelper.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Schedule.idlから
* 2014年5月6日 1時14分02秒 JST
*/

abstract public class SchedulerHelper
{
  private static String  _id = "IDL:schedule/Scheduler:1.0";

  public static void insert (org.omg.CORBA.Any a, schedule.Scheduler that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static schedule.Scheduler extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (schedule.SchedulerHelper.id (), "Scheduler");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static schedule.Scheduler read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SchedulerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, schedule.Scheduler value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static schedule.Scheduler narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof schedule.Scheduler)
      return (schedule.Scheduler)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      schedule._SchedulerStub stub = new schedule._SchedulerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static schedule.Scheduler unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof schedule.Scheduler)
      return (schedule.Scheduler)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      schedule._SchedulerStub stub = new schedule._SchedulerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
