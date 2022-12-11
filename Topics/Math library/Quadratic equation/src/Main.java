import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        double c = in.nextDouble();

        double sqrt = Math.sqrt(b * b - 4 * a * c);
        double r1 = (-b - sqrt) / (2 * a);
        double r2 = (-b + sqrt) / (2 * a);

        if (r1 > r2) {
            System.out.printf("%f %f", r2, r1);
        } else System.out.printf("%f %f", r1, r2);

    }
}