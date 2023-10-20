import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] vector = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int N = vector.length;
        int M = 4;

        long startTimeSequential = System.nanoTime();
        sequentialProcess(vector, 2);
        long endTimeSequential = System.nanoTime();

        long startTimeMultithreaded = System.nanoTime();
        multithreadedProcess(vector, 2, M);
        long endTimeMultithreaded = System.nanoTime();

        System.out.println("Время последовательной обработки (нс): " + (endTimeSequential - startTimeSequential));
        System.out.println("Время многопоточной обработки (нс): " + (endTimeMultithreaded - startTimeMultithreaded));
        System.out.println("Разница: "+((endTimeMultithreaded - startTimeMultithreaded)-(endTimeSequential - startTimeSequential)));
    }

    public static void sequentialProcess(int[] vector, int multiplier) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = vector[i] * multiplier;
        }
    }

    static void multithreadedProcess(int[] vector, int multiplier, int numThreads) throws InterruptedException {
        int chunkSize = (int) Math.ceil((double) vector.length / numThreads);

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, vector.length);
            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    vector[j] = vector[j] * multiplier;
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}