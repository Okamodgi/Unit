import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public void testSequentialProcess() {
        int[] inputVector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int multiplier = 2;

        Main.sequentialProcess(inputVector, multiplier);

        int[] expectedVector = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        assertArrayEquals(expectedVector, inputVector);
    }

    @Test
    public void testMultithreadedProcess() throws InterruptedException {
        int[] inputVector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int multiplier = 2;
        int numThreads = 4;

        Main.multithreadedProcess(inputVector, multiplier, numThreads);

        int[] expectedVector = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        assertArrayEquals(expectedVector, inputVector);

}}