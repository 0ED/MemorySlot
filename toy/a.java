public class a {
    public static void main(String[] args) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            System.out.println("-----------------------------");
            System.out.println("ClassName  : " + stackTraceElement.getClassName());
            System.out.println("FileName   : " + stackTraceElement.getFileName());
            System.out.println("MethodName : " + stackTraceElement.getMethodName());
            System.out.println("LineNumber : " + stackTraceElement.getLineNumber());
            System.out.println("-----------------------------");
        }
    }
}