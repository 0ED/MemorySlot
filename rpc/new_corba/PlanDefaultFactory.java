
package schedule;
import org.omg.CORBA.portable.ValueFactory;
import org.omg.CORBA_2_3.portable.InputStream;
import java.io.Serializable;

public class PlanDefaultFactory implements ValueFactory {
    public Serializable read_value(InputStream in) {
        Plan plan = new PlanImpl();
        in.read_value((Serializable)plan);
        return plan;
    }
}
