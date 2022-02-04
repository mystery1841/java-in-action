package learning.concurrency.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.*;

public class TaskGroupInvokeAllTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<String>> tasks = new ArrayList<>();
        tasks.add(() -> {
            Thread.sleep(2000);
            return "a";
        });
        tasks.add(() -> {
            Thread.sleep(1000);
            return "b";
        });
        tasks.add(() -> {
            return "c";
        });
        long startTime = System.currentTimeMillis();
        List<Future<String>> results = executor.invokeAll(tasks);
        System.out.println(System.currentTimeMillis() - startTime + " millis");
        for (Future<String> result : results) {
            System.out.println(result.get());
        }
        executor.shutdown();
    }

}
