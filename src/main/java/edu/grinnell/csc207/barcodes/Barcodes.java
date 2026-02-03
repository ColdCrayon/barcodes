package edu.grinnell.csc207.barcodes;

public class Barcodes {
    /**
     * Array of encodings for each integer within a UPC-A code
     * The first array indicates the incoding for 0, the second for 1, etc.
     * Each array contains 4 integers indicating the number of blocks before a
     * color switch
     */
    private static int[][] ENCODINGS = {
            { 3, 2, 1, 1 },
            { 2, 2, 2, 1 },
            { 2, 1, 2, 2 },
            { 1, 4, 1, 1 },
            { 1, 1, 3, 2 },
            { 1, 2, 3, 1 },
            { 1, 1, 1, 4 },
            { 1, 3, 1, 2 },
            { 1, 2, 1, 3 },
            { 3, 1, 1, 2 }
    };

    /**
     * Converts the given char to an integer
     * 
     * @param ch the char to be converted
     * @return the char ch as an integer
     */
    public static int toDigit(char ch) {
        if (!Character.isDigit(ch)) {
            return -1;
        }

        return ((int) ch) - 48;
    }

    /**
     * Checks if the given UPC-A code is valid
     * 
     * @param code the code to be checked
     * @return true if the code is valid, else false
     */
    public static boolean isValidCode(String code) {
        if (code.length() != 12) {
            return false;
        }

        for (int i = 0; i < 12; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Computes the check digit for the code
     * 
     * @param code the code to compute the check digit for
     * @return the check digit for the code
     */
    public static int computeCheckDigit(String code) {
        int odds = 0;
        int evens = 0;
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                odds += toDigit(code.charAt(i));
            } else {
                evens += toDigit(code.charAt(i));
            }
        }

        int c = ((3 * odds) + evens) % 10;

        if (c != 0) {
            return 10 - c;
        } else {
            return c;
        }
    }

    /**
     * Prints a white block to the terminal
     */
    public static void printWhite() {
        System.out.print("\033[37m█\033[0m");
    }

    /**
     * Prints a black block to the terminal
     */
    public static void printBlack() {
        System.out.print("\033[30m█\033[0m");
    }

    /**
     * Prints the starting zone of all barcodes
     */
    public static void printStart() {
        // quiet zone
        for (int i = 0; i < 9; i++) {
            printWhite();
        }

        // start zone
        printBlack();
        printWhite();
        printBlack();
    }

    /**
     * Prints the ending zone of all barcodes
     */
    public static void printEnd() {
        // end zone
        printBlack();
        printWhite();
        printBlack();

        // quiet zone
        for (int i = 0; i < 9; i++) {
            printWhite();
        }
    }

    /**
     * Prints a row of the barcode for the corresponding UPC-A code to the terminal
     * 
     * @param code the code defining the barcode
     */
    public static void printBarcodeRow(String code) {
        // start zone
        printStart();

        // loop through first 6 digits
        for (int i = 0; i < 6; i++) {
            // loop through corresponding encoding for each digit
            for (int j = 0; j < 4; j++) {
                // print number of blocks
                for (int k = 0; k < ENCODINGS[toDigit(code.charAt(i))][j]; k++) {
                    if (j % 2 == 0) {
                        printWhite();
                    } else {
                        printBlack();
                    }
                }
            }
        }

        // middle zone
        printWhite();
        printBlack();
        printWhite();
        printBlack();
        printWhite();

        // loop through last 6 digits
        for (int i = 6; i < 12; i++) {
            // loop through corresponding encoding for each digit
            for (int j = 0; j < 4; j++) {
                // print number of blocks
                for (int k = 0; k < ENCODINGS[toDigit(code.charAt(i))][j]; k++) {
                    if (j % 2 == 0) {
                        printBlack();
                    } else {
                        printWhite();
                    }
                }
            }
        }

        // end zone
        printEnd();

        // new line
        System.out.print("\n");
    }

    /**
     * Prints n rows for the corresponding barcode defined by the given UPC-A code
     * 
     * @param args an array containing the UPC-A code and the number of rows to be
     *             printed
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: barcode <UPC-A code> <height>");
            System.exit(1);
        }

        String code = args[0];
        int rows = Integer.parseInt(args[1]);

        if (rows < 1) {
            System.out.println("Height must be a positive integer.");
            System.exit(1);
        }

        if (!isValidCode(code)) {
            System.out.println("Code must be a string of 12 digits.");
            System.exit(1);
        }

        if (!(toDigit(code.charAt(11)) == computeCheckDigit(code))) {
            System.out.println("Expected check digit " +
                    computeCheckDigit(code) +
                    " but found " +
                    code.charAt(11) + ".");
            System.exit(1);
        }

        for (int i = 0; i < rows; i++) {
            printBarcodeRow(code);
        }
    }
}