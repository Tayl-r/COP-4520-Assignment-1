import java.util.ArrayList;
import java.util.List;

// Implementing the Runnable interface
class MultithreadingThing implements Runnable {
    /* start & end have the same purpose as Main
        listOfAllPrimes is needed for synchronization
    */
    private int start;
    private int end;
    private List<Integer> listOfAllPrimes;


    // Constructor
    public MultithreadingThing(int start, int end, List<Integer> listOfAllPrimes ) {
        this.start = start;
        this.end = end;
        this.listOfAllPrimes = listOfAllPrimes;
    }

    @Override
    public void run() {
        List<Integer> isPrimes = primeList(start, end);
        /* MOST IMPORTANT: Synchronizes all threads to one
            thread that is in charge of keeping track of 
            the list of prime.
        */
        synchronized (this.listOfAllPrimes) {
            this.listOfAllPrimes.addAll(isPrimes);
        }
    }

    /* Function allows the algorithm to add 
        and return a list of primes from the range
        that the thread was given.
    */
    private List<Integer> primeList(int start, int end) {
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i <= end; i++)
        {
            if (isPrime(i))
            {
                primes.add(i);
            }
        }
        return primes;
    }

    /*  This is just a basic Prime algorithm 
        that is more efficient than going through
        all of n or just half of n. 
    */
    private boolean isPrime(int n){
        
        if (n <= 1) 
            return false; 
  
        if (n == 2 || n == 3) 
            return true; 
  
        if (n % 2 == 0 || n % 3 == 0) 
            return false; 
  
        for (int i = 5; i <= Math.sqrt(n); i = i + 6) 
            if (n % i == 0 || n % (i + 2) == 0) 
                return false; 
  
        return true; 
    }
}