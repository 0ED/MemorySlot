package schedule;


/**
* schedule/Time.java .
* IDL-to-Javaコンパイラ(ポータブル)、バージョン"3.2"によって生成されました
* Schedule.idlから
* 2014年5月6日 1時14分02秒 JST
*/

public abstract class Time implements org.omg.CORBA.portable.StreamableValue
{
  protected int hour = (int)0;
  protected int minute = (int)0;
  protected int second = (int)0;

  private static String[] _truncatable_ids = {
    schedule.TimeHelper.id ()
  };

  public String[] _truncatable_ids() {
    return _truncatable_ids;
  }

  public abstract int getHour ();

  public abstract int getMinute ();

  public abstract int getSecond ();

  public void _read (org.omg.CORBA.portable.InputStream istream)
  {
    this.hour = istream.read_long ();
    this.minute = istream.read_long ();
    this.second = istream.read_long ();
  }

  public void _write (org.omg.CORBA.portable.OutputStream ostream)
  {
    ostream.write_long (this.hour);
    ostream.write_long (this.minute);
    ostream.write_long (this.second);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return schedule.TimeHelper.type ();
  }
} // class Time
