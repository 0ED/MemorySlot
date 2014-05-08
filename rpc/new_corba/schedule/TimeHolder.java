package schedule;

/**
* schedule/TimeHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Schedule.idlから
* 2014年5月6日 1時14分02秒 JST
*/

public final class TimeHolder implements org.omg.CORBA.portable.Streamable
{
  public schedule.Time value = null;

  public TimeHolder ()
  {
  }

  public TimeHolder (schedule.Time initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = schedule.TimeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    schedule.TimeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return schedule.TimeHelper.type ();
  }

}
