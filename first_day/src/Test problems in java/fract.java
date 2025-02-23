import java.util.Scanner;

public class fract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();
        for (int k = 0; k <= number; k++){
            int kf = fractal(k);
            int numberf = fractal(number);
            int number_kf = fractal(number - k);
            int result = numberf / (kf * number_kf);
            if (k == number) {
                System.out.println(result); 
            } else {
                System.out.print(result + " ");
            }
        }   
        scanner.close();
    }

    public static int fractal(int number){
        int s = 1;
        for (int i = 1; i <= number; i++){
            s *= i;
        }
        return s;
    }
}