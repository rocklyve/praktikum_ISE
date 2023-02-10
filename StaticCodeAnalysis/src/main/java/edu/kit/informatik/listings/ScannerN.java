package edu.kit.informatik.listings;

import java.util.Scanner;

public class ScannerN {
    public static void main(String[] args) {
        try {
            Scanner inputScanner = new Scanner(System.in);
            // Do something with the scanner here.
            inputScanner.close();
        } catch (Error e) {
            System.out.println("Error, " + e.getMessage());
        }
    }
}