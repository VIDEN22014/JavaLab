public class AreaAdder {
    public double area=0;
    public synchronized void areaAdd(double areaToAdd){
        area+=areaToAdd;
    }
}
