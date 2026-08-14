import java.util.*;

public class elseif2 {
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        if(a==b){
            System.out.println("Numbers are equals");
        }else if(a>b){
            System.out.println("A is greate than B"); 
        }else{
            System.out.println("A is Lesser");
        }
    }
    
}
