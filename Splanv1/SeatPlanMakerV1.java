import java.util.Scanner;

public class SeatPlanMakerV1 {
    private int rows;
    private int leftColumns;
    private int rightColumns;

    public static void main(String[] args) {
        SeatPlanMakerV1 seatPlan = new SeatPlanMakerV1();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter row number, left column number, and right column number separated by space:");
            int rows = scanner.nextInt();
            int leftColumns = scanner.nextInt();
            int rightColumns = scanner.nextInt();

            int totalSeats = rows * (leftColumns + rightColumns);

            if (totalSeats > 99) {
                System.out.println("Total number of seats should not exceed 99. Please try again.");
            } else {
                seatPlan.setRows(rows);
                seatPlan.setLeftColumns(leftColumns);
                seatPlan.setRightColumns(rightColumns);
                break;
            }
        }

        seatPlan.displayLayout();
        scanner.close();
    }

    // Setter for rows
    public void setRows(int rows) {
        this.rows = rows;
    }

    // Getter for rows
    public int getRows() {
        return rows;
    }

    // Setter for left columns
    public void setLeftColumns(int leftColumns) {
        this.leftColumns = leftColumns;
    }

    // Getter for left columns
    public int getLeftColumns() {
        return leftColumns;
    }

    // Setter for right columns
    public void setRightColumns(int rightColumns) {
        this.rightColumns = rightColumns;
    }

    // Getter for right columns
    public int getRightColumns() {
        return rightColumns;
    }

    // Method to display the seating layout
    public void displayLayout() {
        int seatNumber = 1;
        System.out.println("Seat Layout:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < leftColumns; j++) {
                System.out.printf("[%02d]", seatNumber++); // Format with leading zero
            }
            System.out.print("   "); // Center aisle
            for (int k = 0; k < rightColumns; k++) {
                System.out.printf("[%02d]", seatNumber++); // Format with leading zero
            }
            System.out.println(); // Move to the next row
        }
    }
}
