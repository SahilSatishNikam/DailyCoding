import java.util.*;
public class instanceVar {
    
    int age;
    String name;

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();
        String name = sc.nextLine();

        System.out.println("Your age is:"+age);
        System.out.println("Your name is:"+name);
    }
    
}
