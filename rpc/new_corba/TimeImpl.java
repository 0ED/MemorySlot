package schedule;
public class TimeImpl extends Time {
    public TimeImpl() {
        hour = 0;
        minute = 0;
        second = 0;
    }
    public TimeImpl(int h, int m, int s) {
        hour = h;
        minute = m;
        second = s;
    }
    public int getHour() {
        return hour;
    }
    public int getMinute() {
        return minute;
    }
    public int getSecond() {
        return second;
    }
    public String toString() {
        return "[TimeImpl]{hour=" + hour +
            ",minute=" + minute + ",second=" +
            second + "}";
    }
}
