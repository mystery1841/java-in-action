package learning.concurrency.synchronize;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockObject {

    private volatile Lock bankLock = new ReentrantLock();
    private Condition sufficientFunds;
    final Map<String, Double> accounts = new HashMap<>();

    @BeforeAll
    public void startupOfAll() {
        sufficientFunds = bankLock.newCondition();
    }

    @Test
    public void testReentrantLock() throws InterruptedException {
    }


}
