package Birds;

public abstract class Bird {
    boolean isUp=false;
    public void up(){
        System.out.println(this.getClass()+" is up");
        isUp=true;
    }
    public void down(){
        System.out.println(this.getClass()+" is down");
        isUp=false;
    }
    public void danger(){
        if (isUp){
            System.out.println(this.getClass()+" is hide");
            isUp=false;
        }
    }
}
