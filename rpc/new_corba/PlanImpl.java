package schedule;
public class PlanImpl extends Plan {  
    public PlanImpl() {
        start = new TimeImpl(1, 1, 1);
        end = new TimeImpl(2, 2, 2);
    }

    public PlanImpl(Time aStart, Time anEnd) {
        start = aStart;
        end = anEnd;
    }

    public Time getStartTime() {
        return start;
    }

    public Time getEndTime() {
        return end;
    }

    public String toString() {
        return ("[PlanImpl]{start=" +
            start + ":end=" + end + "}");
    }
}
