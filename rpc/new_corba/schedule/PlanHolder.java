package schedule;

/**
* schedule/PlanHolder.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Schedule.idlから
* 2014年5月6日 1時14分02秒 JST
*/

public final class PlanHolder implements org.omg.CORBA.portable.Streamable
{
  public schedule.Plan value = null;

  public PlanHolder ()
  {
  }

  public PlanHolder (schedule.Plan initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = schedule.PlanHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    schedule.PlanHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return schedule.PlanHelper.type ();
  }

}
