package learning.library.math;

import java.util.Random;

public class RandomTest {

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int x = 1 + (int) (random.nextDouble() * 10);
            System.out.println(x);
        }
    }
}
