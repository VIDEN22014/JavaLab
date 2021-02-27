package Birds;


public abstract class PredatorBird extends Bird {
    @Override
    public void danger() {
    }
    @Override
    public void down() {
        System.out.println(this.getClass()+" is down\n");
        isUp=false;
    }
}
