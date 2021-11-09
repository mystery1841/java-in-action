package learning.io.console;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class ScannerTest {

    @Test
    public void testScannerDelimiter() {
        Scanner scanner = new Scanner("12,42,78,99,42");
        scanner.useDelimiter(",");
        int[] array = new int[5];
        int i = 0;
        while (scanner.hasNextInt()) {
            array[i] = scanner.nextInt();
            i++;
        }
        assertArrayEquals(new int[]{12, 42, 78, 99, 42}, array);
    }

    @Test
    public void testRegularScan() {
        String threatData = "58.27.82.161@02/10/2005\n" +
                "204.45.234.40@02/11/2005\n" +
                "58.27.82.161@02/11/2005\n" +
                "58.27.82.161@02/12/2005\n" +
                "58.27.82.161@02/12/2005\n" +
                "[Next log section with different data format]";
        Scanner scanner = new Scanner(threatData);
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
        String[] expect = {"Threat on 02/10/2005 from 58.27.82.161",
                "Threat on 02/11/2005 from 204.45.234.40",
                "Threat on 02/11/2005 from 58.27.82.161",
                "Threat on 02/12/2005 from 58.27.82.161",
                "Threat on 02/12/2005 from 58.27.82.161"};
        String[] array = new String[5];
        int i = 0;
        while (scanner.hasNext(pattern)) {
            scanner.next(pattern);
            MatchResult match = scanner.match();
            String ip = match.group(1);
            String date = match.group(2);
            array[i] = String.format("Threat on %s from %s", date, ip);
            i++;
        }
        assertArrayEquals(expect, array);
    }
}
