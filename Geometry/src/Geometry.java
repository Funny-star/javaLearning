import java.util.Scanner;

public class Geometry {
    public static void main(String arg[]) {
        Scanner input = new Scanner(System.in);
        String exitProgram = "n";

        while (!exitProgram.equalsIgnoreCase("y")) {
            printMenu();
            int choice = input.nextInt();

            if (choice == 1) {
                System.out.print("Enter the radius of the circle: ");
                double radius = input.nextDouble();
                double area = circleArea(radius);
                System.out.println("The area of the circle is " + area);
            }
            else if (choice == 2) {
                System.out.print("Enter the length of the rectangle: ");
                double length = input.nextDouble();
                System.out.print("Enter the width of the rectangle: ");
                double width = input.nextDouble();
                double area = rectangleArea(length, width);
                System.out.println("The area of the rectangle is " + area);
            }
            else if (choice == 3) {
                System.out.print("Enter the height of the triangle: ");
                double height = input.nextDouble();
                System.out.print("Enter the base of the triangle: ");
                double base = input.nextDouble();
                double area = triangleArea(base, height);
                System.out.println("The area of the triangle is " + area);
            }
            else if (choice == 4) {
                System.out.print("Enter the radius of the circle: ");
                double radius = input.nextDouble();
                double circumference = circleCircumference(radius);
                System.out.println("The circumference of the circle is " + circumference);
            }
            else if (choice == 5) {
                System.out.print("Enter the length of the rectangle: ");
                double length = input.nextDouble();
                System.out.print("Enter the width of the rectangle: ");
                double width = input.nextDouble();
                double perimeter = rectanglePerimeter(length, width);
                System.out.println("The perimeter of the rectangle is " + perimeter);
            }
            else if (choice == 6) {
                System.out.print("Enter the length of side 1 of the triangle: ");
                double side1 = input.nextDouble();
                System.out.print("Enter the length of side 2 of the triangle: ");
                double side2 = input.nextDouble();
                System.out.print("Enter the length of side 3 of the triangle: ");
                double side3 = input.nextDouble();
                double perimeter = trianglePerimeter(side1, side2, side3);
                System.out.println("The perimeter of the triangle is " + perimeter);
            }

            System.out.print("Do you want to exit the program (Y/N)?: ");
            exitProgram = input.next();
        }

        input.close();
    }

    public static void printMenu() {
        System.out.println("This is a geometry calculator");
        System.out.println("Choose what you would like to calculate");
        System.out.println("1. Find the area of a circle");
        System.out.println("2. Find the area of a rectangle");
        System.out.println("3. Find the area of a triangle");
        System.out.println("4. Find the circumference of a circle");
        System.out.println("5. Find the perimeter of a rectangle");
        System.out.println("6. Find the perimeter of a triangle");
        System.out.println("Enter your choice: ");
    }

    public static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }

    public static double rectangleArea(double length, double width) {
        return length * width;
    }

    public static double triangleArea(double base, double height) {
        return 0.5 * base * height;
    }

    public static double circleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    public static double rectanglePerimeter(double length, double width) {
        return 2 * length + 2 * width;
    }

    public static double trianglePerimeter(double side1, double side2, double side3) {
        return side1 + side2 + side3;
    }
}