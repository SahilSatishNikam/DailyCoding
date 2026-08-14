import java.util.*;

public class andifelse {
    public static void main(String args[]){
        Scanner sc =new Scanner(System.in);
        int age = sc.nextInt();

        boolean iseligible = true;

        if (age >= 18 && iseligible){
            System.out.println("You are eligible to vote");
        }
    }
    
}
