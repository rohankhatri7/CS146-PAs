import java.util.Scanner;

public class PA1Inversion {

    public static int countInversions(int[] array) {
        int n = array.length;
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[i] > array[j]) {
                    count++;
                }
            }
        }

        return count;
    }
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid input");
            System.exit(1);
        }

        int nums = Integer.parseInt(args[0]);
        int[] numbers = new int[nums];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter " + nums + " numbers:");

        for (int i = 0; i < nums; i++) {
            numbers[i] = scanner.nextInt();
        }

        int inversionCount = countInversions(numbers);

        System.out.println("Number of inversions: " + inversionCount);
    }

}
