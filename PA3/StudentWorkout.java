import java.util.ArrayList;
import java.util.Comparator;


public class StudentWorkout {
    String studentName;
    double distance;
    int rhr; // resting heart rate
    int zoneMinutes;
    ArrayList<String> sorting;

    public StudentWorkout(String studentName, double distance, int rhr, int zoneMinutes, ArrayList<String> sorting) {
        this.studentName = studentName;
        this.distance = distance;
        this.rhr = rhr;
        this.zoneMinutes = zoneMinutes;
        this.sorting = sorting;
    }


    // Getters and Setters
    public void setStudentName(String a) {
        this.studentName = a;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setDistance(double a) {
        this.distance = a;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setRhr(int a) {
        this.rhr = a;
    }

    public int getRhr() {
        return this.rhr;
    }

    public void setZoneMinutes(int a) {
        this.zoneMinutes = a;
    }

    public int getZoneMinutes() {
        return zoneMinutes;
    }


    public void setSorting(ArrayList<String> a) {
        this.sorting = a;
    }

    public ArrayList<String> getSorting() {
        return sorting;
    }


    public Comparator<StudentWorkout> getComparator() {
        Comparator<StudentWorkout> cmp;

        switch (this.sorting.get(0)) {
            case "name":

                cmp = Comparator.comparing(StudentWorkout::getStudentName);
                if (this.sorting.size() > 1) {
                    for (int i = 1; i < this.sorting.size(); i++) {
                        if (this.sorting.get(i).equals("distance")) {
                            cmp = cmp.thenComparingDouble(StudentWorkout::getDistance);
                        } else if (this.sorting.get(i).equals("rhr")) {
                            cmp = cmp.thenComparingInt(StudentWorkout::getRhr);

                        } else if (this.sorting.get(i).equals("zoneMinutes")) {
                            cmp = cmp.thenComparingInt(StudentWorkout::getZoneMinutes);

                        } else {
                            break;
                        }
                    }
                }
                break;
            case "distance":
                cmp = Comparator.comparingDouble(StudentWorkout::getDistance);
                if (this.sorting.size() > 1) {
                    for (int i = 1; i < this.sorting.size(); i++) {
                        if (this.sorting.get(i).equals("name")) {
                            cmp = cmp.thenComparing(StudentWorkout::getStudentName);
                        } else if (this.sorting.get(i).equals("rhr")) {
                            cmp = cmp.thenComparingInt(StudentWorkout::getRhr);

                        } else if (this.sorting.get(i).equals("zoneMinutes")) {
                            cmp = cmp.thenComparingInt(StudentWorkout::getZoneMinutes);

                        } else {
                            break;
                        }
                    }
                }

                break;
            case "rhr":
                cmp = Comparator.comparingInt(StudentWorkout::getRhr);
                if (this.sorting.size() > 1) {
                    for (int i = 1; i < this.sorting.size(); i++) {
                        if (this.sorting.get(i).equals("distance")) {
                            cmp = cmp.thenComparingDouble(StudentWorkout::getDistance);
                        } else if (this.sorting.get(i).equals("name")) {
                            cmp = cmp.thenComparing(StudentWorkout::getStudentName);

                        } else if (this.sorting.get(i).equals("zoneMinutes")) {
                            cmp = cmp.thenComparingInt(StudentWorkout::getZoneMinutes);

                        } else {
                            break;
                        }
                    }
                }

                break;
            case "zoneMinutes":
                cmp = Comparator.comparingInt(StudentWorkout::getZoneMinutes);
                if (this.sorting.size() > 1) {
                    for (int i = 1; i < this.sorting.size(); i++) {
                        if (this.sorting.get(i).equals("distance")) {
                            cmp = cmp.thenComparingDouble(StudentWorkout::getDistance);
                        } else if (this.sorting.get(i).equals("rhr")) {
                            cmp = cmp.thenComparingInt(StudentWorkout::getRhr);

                        } else if (this.sorting.get(i).equals("name")) {
                            cmp = cmp.thenComparing(StudentWorkout::getStudentName);

                        } else {
                            break;
                        }
                    }
                }
                break;
            default:
                cmp = null;
                System.out.print("Print a proper sorting type");
                break;
        }


        return cmp;
    }


}

