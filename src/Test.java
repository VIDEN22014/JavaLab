public class Test {
    public static void main(String[] args) throws InterruptedException {
        Forest lis = new Forest();
        for (int i = 0; i < 20; i++) {
            lis.create();
            Thread.sleep(1500);
            lis.checkDanger();
            Thread.sleep(1500);
        }
    }
}
