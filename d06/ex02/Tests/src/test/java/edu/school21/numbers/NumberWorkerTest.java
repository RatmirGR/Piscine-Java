package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41})
    public void isPrimeForPrimes(int num){
        assertTrue(new NumberWorker().isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {49, 50, 51, 52, 54, 55, 56, 57, 58, 60, 62, 63, 64, 65})
    public void isPrimeForNotPrimes(int num){
        assertFalse(new NumberWorker().isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, Integer.MIN_VALUE, 0, -100})
    public void isPrimeForIncorrectNumbers(int num){
        assertThrows(NumberWorker.IllegalNumberException.class, () -> new NumberWorker().isPrime(num));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"})
    public void digitsSum(int actual, int expected){
        assertEquals(expected, new NumberWorker().digitsSum(actual));
    }
}
