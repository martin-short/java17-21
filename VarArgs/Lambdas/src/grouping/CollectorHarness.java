package grouping;

import static java.lang.System.*;

import java.util.function.Consumer;

public class CollectorHarness {

    public static void main(String[] args) {
        long partitionPrimes = execute(PartitionPrimeNumbers::partitionPrimes);
        long partitionPrimesWithCustomCollector = execute(PartitionPrimeNumbers::partitionPrimesWithCustomCollector);

        out.println("Partitioning done in: " + partitionPrimes + " msecs");
        out.println("Partitioning done in: " + partitionPrimesWithCustomCollector + " msecs");
    }

    private static long execute(final Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
            out.println("done in " + duration);
        }
        return fastest;
    }
}