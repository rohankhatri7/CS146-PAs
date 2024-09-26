import java.util.Comparator;
import java.util.ArrayList;

public class Main {

    public static StudentWorkout[] sort(int input, String sortType, ArrayList sorting, boolean randomization) {
        StudentData tmp = new StudentData();

        tmp.createStudentArray(input, sorting);
        Comparator<StudentWorkout> studentWorkoutComparator = tmp.studentArray[1].getComparator();


        if (sortType.equals("quick")) {
            tmp.quickSort(studentWorkoutComparator, randomization);
            return tmp.studentArray;
        } else if (sortType.equals("heap")) {
            tmp.heapSort(studentWorkoutComparator);
            return tmp.studentArray;
        }
        return null;
    }

    public static void main(String[] args) {

        //instantiation of args
        boolean randomized = false;
        int input = Integer.parseInt(args[0]);
        String sortType = args[1];
        //System.out.print(input);
        if (args.length < 3) {
            System.out.println("You need at least 3 arguments");
            System.exit(1);
        }
        if(input < 1){
            System.err.println("Input must be greater than 1");
            System.exit(1);
        }

        ArrayList<String> sortingValues = new ArrayList<>();

        if ((sortType.equals("quick"))|| sortType.equals("heap")) {
            for (int i = 2; i < args.length; i++) {
                if (args[i].equals("name") || args[i].equals("rhr") || args[i].equals("zoneMinutes") || args[i].equals("distance")) {
                    sortingValues.add(args[i]);
                }
            }

        }
        if(sortType.equals("quick")){
            if(Boolean.parseBoolean(args[args.length - 1])) {
                randomized = Boolean.parseBoolean(args[args.length - 1]);
            }
        }
        if(sortType.equals("quick")){
            randomized = args[args.length - 1].equals("random");
        }



        StudentWorkout[] organized = sort(input, sortType, sortingValues, randomized);


        for (int i = 1; i < organized.length; i++) {
            System.out.println(organized[i].getStudentName() + ", " + organized[i].getDistance() + ", " +
                    organized[i].getRhr() + ", " + organized[i].getZoneMinutes());
        }

    }


}

