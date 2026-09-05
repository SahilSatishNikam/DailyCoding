import java.util.*;

public class task{

        int Sub1;
        int Sub2;
        int Sub3;

    public static void main(String args[]){
        
        Scanner sc= new Scanner(System.in);
        int Sub1 = sc.nextInt();
        int Sub2 = sc.nextInt();
        int Sub3 = sc.nextInt();

        System.out.println(Sub1+Sub2+Sub3);
        
        int average = (Sub1+Sub2+Sub3)/3;
        System.out.println(average + "%");
    }
}
