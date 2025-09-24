public class ShapeAreasImpl {
    static double PI = Math.PI;
    public double getAreaParallelopiped(double a, double b ,double c){
        return 2 * ( a * b + b * c + c * a);
    }
    public double getAreaTrapezoid( double b1, double b2, double he){
        return (b1 + b2) / 2 * he;
    }
   public double getAreaOval( double PI ,double b1 ,double b2){
        return (PI * b1 * b2);
   }
}

