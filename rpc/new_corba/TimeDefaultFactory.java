package schedule;
import org.omg.CORBA.portable.ValueFactory;
import org.omg.CORBA_2_3.portable.InputStream;
import java.io.Serializable;

public class TimeDefaultFactory implements ValueFactory {
    public Serializable read_value(InputStream in) {
        Time time = new TimeImpl();
        in.read_value((Serializable)time);
        return time;
    }
}
