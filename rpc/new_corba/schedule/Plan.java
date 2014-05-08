package schedule;


/**
* schedule/Plan.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Schedule.idlから
* 2014年5月6日 1時14分02秒 JST
*/

public abstract class Plan implements org.omg.CORBA.portable.StreamableValue
{
  protected schedule.Time start = null;
  protected schedule.Time end = null;

  private static String[] _truncatable_ids = {
    schedule.PlanHelper.id ()
  };

  public String[] _truncatable_ids() {
    return _truncatable_ids;
  }

  public void _read (org.omg.CORBA.portable.InputStream istream)
  {
    this.start = schedule.TimeHelper.read (istream);
    this.end = schedule.TimeHelper.read (istream);
  }

  public void _write (org.omg.CORBA.portable.OutputStream ostream)
  {
    schedule.TimeHelper.write (ostream, this.start);
    schedule.TimeHelper.write (ostream, this.end);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return schedule.PlanHelper.type ();
  }
} // class Plan
