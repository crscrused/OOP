import java.util.Scanner;

public class SeatPlanMakerV2 {

    private int rows;
    private int leftColumns;
    private int rightColumns;
    private boolean[] seatStatus; // Array to track seat availability

    public static void main(String[] args) {
        SeatPlanMakerV2 seatPlan = new SeatPlanMakerV2();
        Scanner scanner = new Scanner(System.in);

        // Loop until valid seat plan (less than 99 seats) is entered
        while (true) {
            System.out.println("Enter row number, left column number, and right column number separated by space:");
            int rows = scanner.nextInt();
            int leftColumns = scanner.nextInt();
            int rightColumns = scanner.nextInt();

            int totalSeats = rows * (leftColumns + rightColumns);

            // If total number of seats exceeds 99, prompt the user again
            if (totalSeats > 99) {
                System.out.println("Total number of seats should not exceed 99. Please try again.");
            } else {
                seatPlan.setRows(rows);
                seatPlan.setLeftColumns(leftColumns);
                seatPlan.setRightColumns(rightColumns);
                seatPlan.initializeSeatPlan(); // Initialize seat status array
                break;
            }
        }

        // Loop to select seats
        while (true) {
            seatPlan.displayLayout(); // Display the current seat layout
            System.out.println("Enter a seat number to reserve (enter 0 to exit):");
            int seatNumber = scanner.nextInt();

            if (seatNumber == 0) {
                System.out.println("Exiting seat selection...");
                break; // Exit the loop if user enters 0
            }

            if (seatNumber < 1 || seatNumber > seatPlan.getTotalSeats()) {
                System.out.println("Invalid seat number. Please try again.");
                continue;
            }

            if (seatPlan.getSeatStatus(seatNumber - 1)) { // Check if seat is taken
                System.out.println("This seat is already taken. Please choose another one.");
            } else {
                seatPlan.setSeatStatus(seatNumber - 1); // Reserve the seat
                System.out.println("Seat " + seatNumber + " has been reserved.");
            }
        }

        scanner.close(); // Close the scanner to avoid resource leakage
    }

    // Method to set the number of rows
    public void setRows(int rows) {
        this.rows = rows;
    }

    // Method to get the number of rows
    public int getRows() {
        return rows;
    }

    // Method to set the number of columns on the left side
    public void setLeftColumns(int leftColumns) {
        this.leftColumns = leftColumns;
    }

    // Method to get the number of columns on the left side
    public int getLeftColumns() {
        return leftColumns;
    }

    // Method to set the number of columns on the right side
    public void setRightColumns(int rightColumns) {
        this.rightColumns = rightColumns;
    }

    // Method to get the number of columns on the right side
    public int getRightColumns() {
        return rightColumns;
    }

    // Method to initialize seat plan and set seat status to false (not taken)
    public void initializeSeatPlan() {
        int totalSeats = getTotalSeats();
        seatStatus = new boolean[totalSeats]; // Create a boolean array to track seat status
        for (int i = 0; i < totalSeats; i++) {
            seatStatus[i] = false; // All seats are initially available (not taken)
        }
    }

    // Method to get the total number of seats
    public int getTotalSeats() {
        return rows * (leftColumns + rightColumns);
    }

    // Method to display the seat layout, showing taken seats with [XX]
    public void displayLayout() {
        int seatNumber = 1; // Start numbering seats from 1
        System.out.println("Seat Layout:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < leftColumns; j++) {
                printSeat(seatNumber++); // Print seat number or [XX] if taken
            }
            for (int k = 0; k < rightColumns; k++) {
                printSeat(seatNumber++); // Print seat number or [XX] if taken
            }
            System.out.println(); // Move to the next row
        }
    }

    // Helper method to print a seat based on its status
    private void printSeat(int seatNumber) {
        if (seatStatus[seatNumber - 1]) {
            System.out.printf("[XX]"); // Seat is taken
        } else {
            System.out.printf("[%02d]", seatNumber); // Seat is available
        }
    }

    // Method to set seat status (mark seat as taken)
    public void setSeatStatus(int seatIndex) {
        seatStatus[seatIndex] = true; // Mark the seat as taken
    }

    // Method to get seat status (check if seat is taken)
    public boolean getSeatStatus(int seatIndex) {
        return seatStatus[seatIndex]; // Return true if seat is taken, false otherwise
    }
}
