import java.util.*;

public class switchEx {
    public static void main(String args[]){
        Scanner sc =new Scanner(System.in);
        int Button = sc.nextInt();

        switch(Button){
            case 1 -> System.out.println("Hello");
            case 2 -> System.out.println("Namaste");
            case 3 -> System.out.println("Bonjur");
            default -> System.out.println("Invalid Button");
        }
    }
    
}
