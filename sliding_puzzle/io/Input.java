package sliding_puzzle.io;
import java.util.Scanner;

public class Input{

    public Scanner scanner;
    private int rows;
    private int cols;

    public Input() {
        scanner = new Scanner(System.in);
        this.rows = -1;
        this.cols = -1;
    }
    public String inputUsername(){
        System.out.print("Enter a username >>> ");
        return scanner.nextLine();
    }

    public int[] inputPuzzleSize(String username){
        System.out.printf("Alright %s, let's customize your puzzle", username);
        System.out.print("Select your puzzle size.(example 3 4 creates a 3 row 4 column puzzle for you.)\n  >>> ");

        this.rows = scanner.nextInt();
        this.cols = scanner.nextInt();

        return new int[] { this.rows, this.cols };
    }

    public int[] readMoves(){
        int row1, col1, row2, col2;

        while (true) {
            System.out.print(">>> ");

            row1 = scanner.nextInt();
            col1 = scanner.nextInt();
            row2 = scanner.nextInt();
            col2 = scanner.nextInt();

            if (row1 < 0 || row1 >= rows ||
                col1 < 0 || col1 >= cols ||
                row2 < 0 || row2 >= rows ||
                col2 < 0 || col2 >= cols) {
                System.out.printf("❌ Invalid input! Please try again.");
                System.out.printf("Row values must be between 0 and %d, col values between 0 and %d.%n", rows - 1, cols - 1);

            } 
            else if (row1 == row2 && col1 == col2) {
                System.out.println("❌ You cannot swap the same cell with itself. Please try again.");
            } 
            else {
                break;
            }
        }

        return new int[]{row1, col1, row2, col2};
    }

}