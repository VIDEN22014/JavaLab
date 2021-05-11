public class MyThread implements Runnable {
    SimpsonMethod method;

    MyThread(SimpsonMethod method) {
        this.method = method;
    }

    @Override
    public synchronized void run() {
        SimpsonMethod.area += method.Integrate();
    }
}
