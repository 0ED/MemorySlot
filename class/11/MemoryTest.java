import java.lang.*;
import java.util.*;

public class MemoryTest {
    public MemoryTest() { this(10000); }
    public MemoryTest(int loopCount) {
        showMemory();
        heavyProcess(loopCount);
        showMemory();
    }
    
    public void showMemory() {
        Runtime runtime = Runtime.getRuntime(); //コンストラクタは呼び出さない
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long useMemory = totalMemory - freeMemory;
        System.out.println("総メモリ: " + totalMemory);
        System.out.println("空きメモリ: " + freeMemory);
        System.out.println("使用メモリ: " + useMemory);
        System.out.println(
            "使用率: " + 100d * useMemory / totalMemory);
    }
    
    private List heavyProcess(int loopCount) {
        List list = new ArrayList();
        for (int i = 0; i < loopCount; i++) {
            list.add("" + i);
        }
        return list;
    }
    
    public static void main(String[] args) {
        if(args.length == 0) {
            new MemoryTest();
        }
        else {
            new MemoryTest( Integer.parseInt(args[0]) );
        }
    }
}