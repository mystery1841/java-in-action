package learning.basic.type;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BigNumberTest {

    @Test
    public void testBigDecimalDivide() {
        BigDecimal b1 = new BigDecimal("2.1");
        BigDecimal b2 = new BigDecimal("9.2");
        assertThrows(RuntimeException.class, () -> {
            b1.divide(b2);
        });
    }
}
