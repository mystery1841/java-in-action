package learning.concurrency.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCompletionServiceTest {

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
        var service = new ExecutorCompletionService<String>(executor);
        for (Callable<String> task : tasks) service.submit(task);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(service.take().get());
        }


    }
}
