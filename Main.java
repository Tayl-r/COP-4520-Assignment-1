import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        // Begin start time to record execution time
        long startTime = System.currentTimeMillis();

        // Number of threads, 10^8, and 1/8 of the range
        int numberOfThreads = 8;
        int maxNum = 100000000;
        int eighthInterval = maxNum / numberOfThreads;

        /* Different max ranges to test */ 
        // int maxNum = 1000;
        // int maxNum = 10000;
        // int maxNum = 100000;
        // int maxNum = 0;
        // int maxNum = 1;

        
        /* - List for threads 
           - List for primes (Important: pass by reference)
        */ 
        List<Thread> myThreadsArray = new ArrayList<>();
        List<Integer> listOfPrimes = new ArrayList<>();

        /*  Parameters to send with MultithreadingThing */
        int start;
        int end;

        // excludes 0 & 1 if this is tested
        if (maxNum <= 1){
            // Calculates execution time
            long endtime = System.currentTimeMillis();
            long executionTime = endtime - startTime;

            FileWriter fileWriter = new FileWriter("primes.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print("No output \nExecution Time: "+ executionTime );
            printWriter.close();
        }
        else{

         // Creating and starting the threads
            for (int i = 0; i < numberOfThreads; i++)
            {
                // Assigns each thread's start & end in intervals of 1/8
                start = i * eighthInterval + 1;
                end = (i + 1) * eighthInterval;

                // Necessarry to run each thread when implementing Runnable interface
                MultithreadingThing threadClassThing = new MultithreadingThing(start, end, listOfPrimes);
                Thread thread = new Thread(threadClassThing);
                myThreadsArray.add(thread);
                thread.start();
            }
        

            // Need to use .join() to make sure thread ended
            // and this allows me to return the list of primes
            // in MultithreadingThing class
            for (Thread threads : myThreadsArray)
            {
                try {
                    threads.join();
                } catch (InterruptedException e) {
                }
            }

            // Calculates execution time
            long endtime = System.currentTimeMillis();
            long executionTime = endtime - startTime;

            // Calculates Sum of all primes
            long sum = calculateSum(listOfPrimes);

            // Steps to write to an output file
            FileWriter fileWriter = new FileWriter("primes.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Final print statements to output file
            printWriter.print("Execution Time: " + executionTime + " milliseconds\n");
            printWriter.print("Total number of primes: " + listOfPrimes.size());
            printWriter.print("\nSum of all the prime numbers: " + sum + "\n");
            getTopTen(listOfPrimes, printWriter);
            printWriter.close();

        } // end of else

    }

    /* Calculates the sum using streams to covert int to long */
    private static long calculateSum(List<Integer> numbers) {
        return numbers.stream().mapToLong(Integer::longValue).sum();
    }

    /* Function to return the Top Ten Primes */
    private static void getTopTen(List<Integer> primeList, PrintWriter printWriter)
    {
        int ten = 10;
        printWriter.print("Top Ten Maximum Primes, lowest to highest: [");
        for(int i = primeList.size() - ten; i < primeList.size(); i++)
        {
            printWriter.print("" + primeList.get(i) + " ");
        }
        printWriter.print("]");
    }

}
