import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class PA2Search {
    public static void main(String[] args) {
    	//edge case
    	if(args.length !=2) {
    		System.out.print("Invalid input");
    		System.exit(1);
    	}
        int numberOfSearchTerms = Integer.parseInt(args[0]);
        String filePath = args[1];
        String[] names = new String[numberOfSearchTerms];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < numberOfSearchTerms; i++) {
            names[i] = scan.nextLine();
        }
        
        long start = System.currentTimeMillis(); //time runtime
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String lineTracker;
            int lineNumber = 0;
            boolean foundterm = false; 
            System.out.printf("%-5s %-9s  %-5s%n", "LINE", "POSITION", "NAME");
            while ((lineTracker = reader.readLine()) != null) {
                lineNumber++;

                for (String name : names) {
                    Pattern pattern = Pattern.compile("\\b" + Pattern.quote(name) + "\\b");
                    Matcher matcher = pattern.matcher(lineTracker);

                    while (matcher.find()) {
                        int startPosition = matcher.start() + 1; 
                        System.out.printf("%-5d %-10d %-5s%n", lineNumber, startPosition, name);
                    	foundterm = true;
                    }
                }
            }
            if(!foundterm) {
            	System.out.print("Input was not found the text file."); //input does not exist in file
            }

            reader.close(); //close reader
        } catch (IOException e) {
            e.printStackTrace();
        }
        long elapsed = System.currentTimeMillis() - start; //calculate runtime
        System.out.print("Elapsed time: " + elapsed + " milliseconds");
    }
}
