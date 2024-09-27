import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.util.ArrayList;

public class TreeAlgorithms {

    public static void main(String[] args) {
        //5a
        BinaryTree b = new BinaryTree();
        Tree23 t = new Tree23(3);
        ArrayList<String> a = new ArrayList<>();
        ArrayList<String> organized = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("unsorted_names_160k.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                a.add(line);
                b.insert(line);
                t.insert(line);
            }

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            return;
        }

        //Used to create random names from the unsorted_names_160k.txt
        BinaryTree b2 = new BinaryTree();
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int randomIndex = r.nextInt(a.size());
            b2.insert((String) a.get(randomIndex));
        }

        ArrayList<String> a2 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("4a.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                a2.add(line);
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return;
        }
        //Making an organized array list
        try (BufferedReader br = new BufferedReader(new FileReader("sorted.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                organized.add(line);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return;
        }
        //5b
        test23Search(t, a2);

        //5c
        testBinarySearch(b, a2);

        //5d
        testLinearSearch(a, a2);

        //5e
        testBinaryArraySearch(organized, a2);
    }

    public static void test23Search(Tree23 tree, ArrayList<String> a2){
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0.0;
        //made to do the 100 trials
        for(int i = 0; i < 100; i++){
            long start = System.currentTimeMillis();
            for(int j = 0; j < a2.size(); j++){
                tree.search(a2.get(j));
            }
            long end = System.currentTimeMillis();
            if ((end - start) < min) {
                min = end - start;
            }
            if ((end - start) > max) {
                max = end - start;
            }
            sum += end - start;
        }
        double average = sum / 100;
        System.out.println("Tree23 Search: " + "Min: " + min + " Max: " + max + " Avg: " + average);
    }



    public static void testBinarySearch(BinaryTree b, ArrayList<String> a2) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0.0;
        for (int i = 0; i < 100; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < a2.size(); j++) {
                b.search(a2.get(j));
            }
            long end = System.currentTimeMillis();
            if ((end - start) < min) {
                min = end - start;
            }
            if ((end - start) > max) {
                max = end - start;
            }
            sum += end - start;
        }
        double average = sum / 100;
        System.out.println("Binary Tree Search: " + "Min: " + min + " Max: " + max + " Avg: " + average);

    }

    public static void testLinearSearch(ArrayList<String> a, ArrayList<String> a2) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0.0;
        for(int i = 0; i < 100; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < a2.size(); j++) {
                for (int k = 0; k < a.size(); k++) {
                    if (a2.get(j).equals(a.get(k))) {
                        break;
                    }
                }
            }
            long end = System.currentTimeMillis();
            if ((end - start) < min) {
                min = end - start;
            }
            if ((end - start) > max) {
                max = end - start;
            }
            sum += end - start;
        }
        double average = sum / 100.0;
        System.out.println("Linear Search: " + "Min: " + min + " Max: " + max + " Avg: " + average);

    }

    public static void testBinaryArraySearch(ArrayList<String> organized, ArrayList<String> a2) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0.0;
        for(int i =0; i < 100; i++) {
            for (int j = 0; j < a2.size(); j++) {
                long start = System.currentTimeMillis();
                binarySearch(organized, a2.get(j));
                long end = System.currentTimeMillis();
                if ((end - start) < min) {
                    min = end - start;
                }
                if ((end - start) > max) {
                    max = end - start;
                }
                sum += end - start;
            }
        }
        double average = sum / 100;
        System.out.println("Binary Array Search: " + "Min: " + min + " Max: " + max + " Avg: " + average);

    }

    public static boolean binarySearch(ArrayList<String> arr, String x) {
        int low = 0;
        int high = arr.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int res = x.compareTo(arr.get(mid));
            if (res == 0){
                return true;
            }
            if (res < 0) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }
}

