import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);

        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();

        double angleR;
        double mod1 = Math.sqrt(x1 * x1 + y1 * y1);
        double mod2 = Math.sqrt(x2 * x2 + y2 * y2);

        double numerator = x1 * x2 + y1 * y2;
        double denominator = mod1 * mod2;
        angleR = Math.acos(numerator / denominator);
        double angleDeg = Math.toDegrees(angleR);

        System.out.println(angleDeg);
    }
}
