import java.util.*;
public class allinone {
     static String name = "This is Static Variable";
    int  age = 20;
    public static void main(String args[]){
        String name1 = "Sahil";

        Scanner sc = new Scanner(System.in);
        int age = sc.nextInt();

        System.out.println("Your age is: " + age);
        System.out.println(name1);
        System.out.println("name is: "+name);
    }
    
}
