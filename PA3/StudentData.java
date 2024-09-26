import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;


public class StudentData {


    StudentWorkout[] studentArray;
    ArrayList<String> sorting;


    public void createStudentArray(int input, ArrayList<String> sorting) {

        try {

            this.studentArray = new StudentWorkout[input + 1];
            this.sorting = sorting;
            Scanner scan = new Scanner(System.in);
            for (int i = 1; i < input + 1; i++) {
                String studentName = scan.next();
                double distance = scan.nextDouble();
                int rhr = scan.nextInt();
                int zoneMinutes = scan.nextInt();
                this.studentArray[i] = new StudentWorkout(studentName, distance, rhr, zoneMinutes, sorting);
            }

        } catch (Exception e) {
            System.err.print("Error");
        }

    }


    public void swap(int i, int j) {
        if (i == j) {
            return;
        }

        StudentWorkout temp = this.studentArray[i];
        this.studentArray[i] = this.studentArray[j];
        this.studentArray[j] = temp;
    }

    public void quickSort(Comparator<StudentWorkout> cmp, boolean randomized) {
        if (randomized) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(1, this.studentArray.length);
            swap(randomIndex, this.studentArray.length - 1);
        }
        quickSort(1, this.studentArray.length - 1, cmp);
    }

    public void quickSort(int low, int high, Comparator<StudentWorkout> cmp) {
        if (low < high) {
            int pivotIndex = partition(low, high, cmp);
            quickSort(low, pivotIndex - 1, cmp);
            quickSort(pivotIndex + 1, high, cmp);
        }
    }


    public int partition(int low, int high, Comparator<StudentWorkout> cmp) {
        StudentWorkout pivot = this.studentArray[high];
        int i = low - 1;


        for (int j = low; j < high; j++) {
            if (cmp.compare(pivot, this.studentArray[j]) > 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    public void heapSort(Comparator<StudentWorkout> cmp) {
        int heapSize = this.studentArray.length;

        for (int subroot = heapSize / 2; subroot >= 1; subroot--) {
            maxHeapify(subroot, heapSize, cmp);
        }


        // Heap sort
        for (int end = heapSize - 1; end > 1; end--) {
            swap(1, end);
            maxHeapify(1, end, cmp);
        }
    }


    public void maxHeapify(int i, int heapSize, Comparator<StudentWorkout> cmp) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = i;


        if (left < heapSize && cmp.compare(this.studentArray[left], this.studentArray[largest]) > 0) {
            largest = left;
        }

        if (right < heapSize && cmp.compare(this.studentArray[right], this.studentArray[largest]) > 0) {
            largest = right;
        }


        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest, heapSize, cmp);
        }
    }


}

