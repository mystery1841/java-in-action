package learning.concurrency.thread;

public class ThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            try {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
                }
            } finally {
                System.out.println("interrupt");
            }
        };
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
}
