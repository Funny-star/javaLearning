package java_study.test1;


public class shapeAreaCalculator {
    public static void main(String[] args){
        shape cir = new Circle();
        shape rec = new Rectangle(1,5);
        System.out.println(cir.getArea());
        System.out.println(rec.getArea());
    }
}
