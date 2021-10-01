package learning.basic.console;

import java.io.Console;

public class PasswordInputTest {

    public static void main(String[] args) {
        Console cons = System.console();
        String username = cons.readLine("User name: ");
        char[] passwd = cons.readPassword("Password: ");
    }
}
