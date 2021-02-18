import java.util.Arrays;

class Hashmap {
    int[] mass = new int[100];

    Hashmap(int x) {
        Arrays.fill(mass,x);
    }
}

public class Lab1 {


    public static void main(String[] args) {
        int x = 1908;
        System.out.println(++x);
        Hashmap hashmap = new Hashmap(x);
        System.out.println(hashmap.mass[0]);
    }
}
