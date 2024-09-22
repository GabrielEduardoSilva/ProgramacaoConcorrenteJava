import java.util.concurrent.Callable;

public class Benchmarker {



    public static BenchmarkResult benchmark(Callable<Void> func) throws Exception {
        int times = 10;
        long totalTime = 0;
        for (int i = 0; i < times; i++) {
            long inicio = System.currentTimeMillis();
            func.call();
            long ultimo = System.currentTimeMillis();
            totalTime += (ultimo - inicio);
        }

        long mean = totalTime / (long)times;
        System.out.println("Mean: " + mean);
        return new BenchmarkResult(totalTime, mean);
    }

}
