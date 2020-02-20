package slogo;

import slogo.backendexternal.Parser;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        Parser p = new Parser();
        p.addPatterns("English");
        p.testParsing();
    }
}
