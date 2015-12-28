import java.util.Scanner;

/**
 * Created by YuGa on 12/23/15.
 */
public class Player {
    String name;
    int getShoot(){
        Scanner scanner = new Scanner(System.in);
        String s;
        s = scanner.nextLine();
        System.out.printf("you enter: %s\n", s);
        int shoot = Integer.parseInt(s);
        return shoot;
    }
    void getName(){
        System.out.println("Please enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String name;
        name = scanner.nextLine();
        System.out.println("Hi "+ name);
        this.name = name;
    }
}
