package schedule;
public class SimpleScheduler extends SchedulerPOA {
    private Plan myPlan;

    public SimpleScheduler() {
        Time start = new TimeImpl(12, 34, 56);
        Time end = new TimeImpl(23,45,6);
        
        myPlan = new PlanImpl(start, end);
    }

    public Plan getPlan() {
        return myPlan;
    }
}
