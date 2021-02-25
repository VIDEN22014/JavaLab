import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        int x;
        while (true){
            x=random.nextInt(2);
            System.out.println(x);
        }
    }  
}
