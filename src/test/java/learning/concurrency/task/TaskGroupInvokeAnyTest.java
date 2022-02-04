package learning.concurrency.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TaskGroupInvokeAnyTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> {
            System.out.println("a start");
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("a canceled");
            }
            return "a";
        });
        tasks.add(() -> {
            System.out.println("b start");
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("b canceled");
            }
            return "b";
        });
        tasks.add(() -> {
            System.out.println("c start");
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("c canceled");
            }
            return "c";
        });
        long startTime = System.currentTimeMillis();
        String result = executor.invokeAny(tasks);
        System.out.println(System.currentTimeMillis() - startTime + " millis");
        System.out.println(result);
        executor.shutdown();
    }

}
