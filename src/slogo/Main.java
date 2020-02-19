package slogo;

import slogo.BackEndExternal.Parser;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        Parser p = new Parser();
        p.addPatterns("English.properties");
        p.testParsing();
    }
}
