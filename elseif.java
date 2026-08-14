import java.util.*;

public class elseif {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if(num%2==0){
            System.out.println("Number is even");
        }
        else if(num%2==1){
            System.out.println("Numbert is odd");
        }else{
            System.out.println("Number is not valid");
        }
    }
    
}
