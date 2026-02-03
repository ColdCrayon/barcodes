package edu.grinnell.csc207.exploration;

import java.util.Scanner;

public class StringExploration {

    /**
     * Concatnate strings in an array with a comma
     * 
     * @param strs
     * @return New format string.
     */
    public static String intersperse(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            result = result + "," + strs[i];
        }
        return result;
    }

    /**
     * * Reorders a name from Last,First,Middle to First Middle Last.
     * 
     * @param fullName
     * @return The new formatted name string.
     */
    public static String parseName(String fullName) {
        String[] parts = fullName.split(",");
        if (parts.length < 3) {
            return fullName;
        }
        return parts[1].trim() + " " + parts[2].trim() + " " + parts[0].trim();
    }

    /**
     * * Asks the user a question and waits for a response.
     * Accepts'y', 'yes', 'n', 'no'.
     * 
     * @param question
     * @return true if the user responds correctly, false otherwise.
     */
    public static boolean forgivingPrompt(String question) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            System.out.print(question + " (y/n): ");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("y") || input.equals("yes") || input.equals("yep"))
                return true;
            if (input.equals("n") || input.equals("no") || input.equals("nope"))
                return false;

            System.out.println("Try again");
        }

        scanner.close();

        return false;
    }

    // N.B., to run this program, use the following maven command to specify
    // this file as the program entry point rather than the class specified in
    // the pom.xml file:
    //
    // mvn compile exec:java -q
    // "-Dexec.mainClass=edu.grinnell.csc207.exploration.StringExploration"
    public static void main(String[] args) {

        String[] strs = { "ab", "cd", "ef" };
        System.out.println(intersperse(strs));

        String inputName = "Turing,Alan,Mathison";
        System.out.println(parseName(inputName));
    }
}
