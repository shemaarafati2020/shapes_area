import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ShapeAreasImpl Impl = new ShapeAreasImpl();
        ShapeAreaModel Model = new ShapeAreaModel();

        System.out.println("enter area of parallelopiped");
        System.out.println("enter value of a for parallelopiped:");
        double apar = in.nextDouble();
        System.out.println("enter value of b for parallelopiped:");
        double bpar = in.nextDouble();
        System.out.println("enter value of c for parallelopiped:");
        double cpar = in.nextDouble();
        Model.setApar(apar);
        Model.setBpar(bpar);
        Model.setCpar(cpar);

        System.out.println("enter area of trapezoid");
        System.out.println("enter value of b1 for trapezoid:");
        double b1tra = in.nextDouble();
        System.out.println("enter value of b2 for trapezoid:");
        double b2tra = in.nextDouble();

        System.out.println("enter value of he for trapezoid:");
        double hetra = in.nextDouble();
        Model.setB1tra(b1tra);
        Model.setB2tra(b2tra);
        Model.setHetra(hetra);

        System.out.println("enter area of oval");
        System.out.println("enter value of b1 for oval:");
        double b1ova = in.nextDouble();
        System.out.println("enter value of b2 for oval:");
        double b2ova = in.nextDouble();


        Model.setB1ova(b1ova);
        Model.setB2ova(b2ova);

        System.out.println("parallelopiped area: " + Impl.getAreaParallelopiped(Model.getApar(), Model.getBpar(), Model.getCpar()));
        System.out.println("trapezoid area: " + Impl.getAreaTrapezoid(Model.getB1tra(), Model.getB2tra(), Model.getHetra()));
        System.out.println("oval area is: " + Impl.getAreaOval(ShapeAreasImpl.PI, Model.getB1ova(), Model.getB2ova()));

    }
}