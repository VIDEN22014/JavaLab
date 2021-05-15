public class MyThread implements Runnable {
    SimpsonMethod method;
    AreaAdder adder;

    MyThread(SimpsonMethod method,AreaAdder adder) {
        this.method = method;
        this.adder=adder;
    }

    @Override
    public void run() {
        this.adder.areaAdd(this.method.Integrate());
    }
}
