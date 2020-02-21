package slogo;

import slogo.backendexternal.parser.Parse;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        Parse p = new Parse();
        p.addPatterns("English");
        p.testParsing();
    }
}
