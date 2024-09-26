import java.util.Scanner;

public class PA1Sort {
	

	public static void main(String[] args) {
		int a[];
		if (args.length != 2) {
			System.out.println("Usage: PA1Sort [insertion|bubble] <number_of_elements>");
			System.exit(1);
		}

		String sortType = args[0];

		try {
			int numberOfElements = Integer.parseInt(args[1]);

			int[] numbers = new int[numberOfElements];
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter " + numberOfElements + " numbers:");

			for (int i = 0; i < numberOfElements; i++) {
				numbers[i] = scanner.nextInt();
			}

			if (sortType.equals("insertion")) {
				a = insertionSort(numbers);
			} else if (sortType.equals("bubble")) {
				a = bubbleSort(numbers);
			} else {
				System.out.println("Invalid sort type. Supported types: insertion, bubble");
				System.exit(1);
			}
			System.out.println("Sorted list:");
	        for (int num : numbers) {
	            System.out.print(num + " ");
	        }

		} catch (NumberFormatException e) {
			System.out.println("Invalid number of elements. Please provide a valid integer.");
			System.exit(1);
		}

	}
	
	public static int[] insertionSort(int[] nums) {
		int n = nums.length;
		for (int i = 1; i < n; i++) {
			int key = nums[i];
			int j = i - 1;
			while (j >= 0 && nums[j] > key) {
				nums[j + 1] = nums[j];
				j--;
			}
			nums[j + 1] = key;
		}
		return nums;
	}

	public static int[] bubbleSort(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = 0; j < nums.length - 1 - i; j++) {
				if (nums[j] > nums[j + 1]) {
					int tmp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = tmp;
				}
			}
		}
		return nums;
	}

}