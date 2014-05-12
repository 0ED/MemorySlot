package schedule;

/**
* schedule/SchedulerHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Schedule.idlから
* 2014年5月6日 1時14分02秒 JST
*/

public final class SchedulerHolder implements org.omg.CORBA.portable.Streamable
{
  public schedule.Scheduler value = null;

  public SchedulerHolder ()
  {
  }

  public SchedulerHolder (schedule.Scheduler initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = schedule.SchedulerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    schedule.SchedulerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return schedule.SchedulerHelper.type ();
  }

}
