public class Main {
    public static void main(String[] args) {
        try {
            Scanner inputScanner = new Scanner(System.in);
            // Do something with the scanner here.
            inputScanner.close();
        } catch (SomeException e) {
            System.out.println("Error, " + e.getMessage());
        }
    }
}