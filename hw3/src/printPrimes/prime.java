package printPrimes;

public class prime {
    public static Boolean isDivisable(int prime, int curPrime) {
        if (curPrime % prime == 0) {
            return true;
        } else
            return false;
    }

    public static void printPrimes(int n) {
        int curPrime; // Value currently considered for primeness
        int numPrimes; // Number of primes found so far.
        boolean isPrime; // Is curPrime prime?
        int[] primes = new int[100]; // The list of prime numbers.

        // Initialize 2 into the list of primes.
        primes[0] = 2;
        numPrimes = 1;
        curPrime = 2;
        while (numPrimes < n) {
            curPrime++; // next number to consider ...
            isPrime = true;
            for (int i = 0; i <= numPrimes - 1; i++) { // for each previous
                                                        // prime.
                if (isDivisable(primes[i], curPrime)) { // Found a divisor,
                                                        // curPrime is not
                                                        // prime.
                    isPrime = false;
                    break; // out of loop through primes.
                }
            }
            if (isPrime) { // save it!
                primes[numPrimes] = curPrime;
                numPrimes++;
            }
        } // End while

        // Print all the primes out.
        for (int i = 0; i <= numPrimes - 1; i++) {
            System.out.println("Prime: " + primes[i]);
        }
    } // end printPrimes
}
