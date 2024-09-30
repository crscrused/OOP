import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HackIngress{

    // Converts a time in HHMM format to minutes since start of the day.
    private static int toMinutes(int time) {
        int hours = time / 100;
        int minutes = time % 100;
        return hours * 60 + minutes;
    }

    // Parses the hack times and converts them into absolute minutes, considering day transitions.
    private static int[] parseHackTimes(int n, int[] times) {
        int[] hackTimes = new int[n];
        int day = 0;
        hackTimes[0] = toMinutes(times[0]); // First hack time is always on the same day
        
        for (int i = 1; i < n; i++) {
            int current = toMinutes(times[i]);
            if (current <= hackTimes[i - 1] % 1440) {
                day += 1;  // New day detected
            }
            hackTimes[i] = current + day * 1440;
        }
        return hackTimes;
    }

    // Counts the number of successful hacks based on the rules provided.
    private static int countSuccessfulHacks(int n, int[] times) {
        int[] hackTimes = parseHackTimes(n, times);
        int successfulHacks = 0;
        int currentCycleStart = -1;
        int currentCycleEnd = -1;
        int hacksInCycle = 0;
        int lastHackTime = -1;

        for (int hackTime : hackTimes) {
            if (currentCycleStart == -1 || hackTime >= currentCycleEnd) {
                // Start a new cycle
                currentCycleStart = hackTime;
                currentCycleEnd = currentCycleStart + 240;  // 4 hours = 240 minutes
                hacksInCycle = 1;
                successfulHacks++;
                lastHackTime = hackTime;
            } else {
                if (hacksInCycle >= 4) {
                    // Maximum of 4 hacks per cycle
                    continue;
                }
                if (hackTime >= lastHackTime + 5) {
                    // Successful hack if 5 minutes cooldown is respected
                    hacksInCycle++;
                    successfulHacks++;
                    lastHackTime = hackTime;
                }
            }
        }
        return successfulHacks;
    }

    public static void main(String[] args) {
        Scanner sc = null;
        PrintWriter pw = null;

        try {
            // Initialize Scanner to read from 'input.in'
            sc = new Scanner(new File("HackIngress.in"));

            // Initialize PrintWriter to write to 'output.out'
            pw = new PrintWriter(new File("HackIngress.out"));

            // Read the input line by line
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                int n = Integer.parseInt(parts[0]);  // First element is the number of hacks

                if (n < 2 || n > 1000) { // Assuming n should be between 2 and 1000
                    pw.println("0");
                    continue;
                }

                if (parts.length < n + 1) { // Ensure there are enough hack times
                    pw.println("0");
                    continue;
                }

                int[] times = new int[n];
                boolean valid = true;
                for (int i = 0; i < n; i++) {
                    try {
                        times[i] = Integer.parseInt(parts[i + 1]);
                        if (times[i] < 0 || times[i] > 2359 || (times[i] % 100) >= 60) {
                            // Invalid time format
                            valid = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        break;
                    }
                }

                if (!valid) {
                    pw.println("0");
                    continue;
                }

                int result = countSuccessfulHacks(n, times);
                pw.println(result);
            }

            System.out.println("Processing complete. Check 'output.out' for results.");

        } catch (FileNotFoundException e) {
            System.err.println("Input file not found.");
        } finally {
            if (sc != null) {
                sc.close();
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
}
