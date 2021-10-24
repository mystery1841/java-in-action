package learning.basic.flow;

import java.util.Scanner;

public class SwitchTest {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Select an option (1, 2, 3, 4) ");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("hello world");
                break;
            default:
                break;
        }

    }
}
