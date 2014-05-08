package schedule;

public abstract class Time 
    implements org.omg.CORBA.portable.StreamableValue
{
        protected int hour;
        protected int minute;
        protected int second;

        public abstract int getHour();
        public abstract int getMinute();
        public abstract int getSecond();

        static final String[] _ids_list = {
                "IDL:schedule/Time:1.0"
        };

        public String [] _truncatable_ids() {
                return _ids_list;
        }

        public void _read(org.omg.CORBA.portable.InputStream is) {
                hour = is.read_long();
                minute = is.read_long();
                second = is.read_long();
        }

        public void _write(org.omg.CORBA.portable.OutputStream os) {
                os.write_long(hour);
                os.write_long(minute);
                os.write_long(second);
        }

        public org.omg.CORBA.TypeCode _type() {
                return schedule.TimeHelper.type();
        }
}
