package schedule;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

/**
 * Client.java
 *
 * @author <a href="mailto:torutk@alles.or.jp">Toru TAKAHASHI</a>
 * @version
 */
public class Client {
   
    public static void main(String[] args) {
        Scheduler scheduler = null;
        try {
            ORB orb = ORB.init(args, null);
            System.out.println("ORB initialized.");
            org.omg.CORBA.Object objNs =
                orb.resolve_initial_references("NameService");
            NamingContext rootNameContext =
                NamingContextHelper.narrow(objNs);
            System.out.println("NamingContext aquired.");
            NameComponent nc =
                new NameComponent("TheScheduler", "Object");
            NameComponent[] path = {nc};
            scheduler =
                SchedulerHelper.narrow(rootNameContext.resolve(path));
            System.out.println("TheScheduler Object aquired.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            Plan plan = scheduler.getPlan();
            System.out.println("Server Plan = " + plan);
            Person person = scheduler.getPerson();
            System.out.println("Server Person = " + person);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
} // Client
