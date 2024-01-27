# COP-4520-Assignment-1
Programming Language: Java

Compile and Run in Command Line: javac Main.java MultithreadingThing.java && java Main

Summary: This program/assignment ask us to output the following of 10^8: <execution time> <total number of primes found> <sum of all primes found>
<top ten maximum primes, listed in order from lowest to highest>. The program executes a multithreading approach by implementing the Runnable interface. In addition, a List<Integer> is the only thing mainly needed to produce the output. Since the List<Integer> is passed by reference, a synchronized block will allow me to join all the results of 8 threads and combining them into one. After calling .join(), the List<Integer> in Main is able to receive the updated list that was passed in the Runnable interface class. I used the a simple algorithm to find primes by only searching through the square root of the number being tested. 

There are commented lines of code that were used to test various ranges (0, 1, 1000, 10000, etc.) other than the 10^8. The results were much faster than if the program was not running in a multithread approach.
