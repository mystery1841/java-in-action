package learning.advanced.process;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class ProcessTest {

    @Test
    public void testProcess() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("cmd", "/C", "ping", "www.baidu.com");
        Process process = builder.start();
        process.onExit().thenAccept(
                p -> {
                    try (var in = new Scanner(process.getInputStream(), "GBK")) {
                        while (in.hasNextLine()) {
                            System.out.println(in.nextLine());
                        }
                    }
                });
        Thread.sleep(10000L);
    }

    @Test
    public void testProcessHandle() {
        ProcessHandle handle = ProcessHandle.current();
        long pid = handle.pid();
        System.out.println(pid);
        Optional<ProcessHandle> parent = handle.parent();
        Stream<ProcessHandle> children = handle.children();
        Stream<ProcessHandle> descendants = handle.descendants();
        parent.flatMap(processHandle -> processHandle.info().command())
                .ifPresent(System.out::println);
    }
}
