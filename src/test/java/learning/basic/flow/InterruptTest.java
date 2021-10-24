package learning.basic.flow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterruptTest {

    @Test
    public void testBreakNestedLoop() {
        int i, j, x = 0, y = 0;

        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (j == 5) {
                    break;
                }
                y = j;
            }
            x = i;
        }

        assertEquals(9, x);
        assertEquals(4, y);
    }

    @Test
    public void testContinueNestedLoop() {
        int i, j, x = 0, y = 0;

        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (j == 5) {
                    continue;
                }
                y = j;
            }
            x = i;
        }

        assertEquals(9, x);
        assertEquals(9, y);
    }

    @Test
    public void testLabeledBreakNestedLoop() {
        int i, j, x = 0, y = 0;

        data:
        for (i = 0; i < 10; i++) {
            x = i;
            for (j = 0; j < 10; j++) {
                if (j == 5) {
                    break data;
                }
                y = j;
            }

        }

        assertEquals(0, x);
        assertEquals(4, y);
    }

    @Test
    public void testLabeledContinueNestedLoop() {
        int i, j, x = 0, y = 0;

        data:
        for (i = 0; i < 10; i++) {
            x = i;
            for (j = 0; j < 10; j++) {
                if (j == 5) {
                    continue data;
                }
                y = j;
            }

        }

        assertEquals(9, x);
        assertEquals(4, y);
    }
}
