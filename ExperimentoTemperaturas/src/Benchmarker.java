import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class Benchmarker {

	static int count = 1;

    public static BenchmarkResult benchmark(Callable<Void> func) throws Exception {
        int times = 10;
        long totalTime = 0;
        
        PrintWriter writer = new PrintWriter("resultados/versao_" + count + ".txt", "UTF-8");
        
        //System.out.println("Experimento " + count);
        writer.println("Experimento " + count);
        for (int i = 0; i < times; i++) {
            long inicio = System.currentTimeMillis();
            func.call();
            long ultimo = System.currentTimeMillis();
            long currTime = (ultimo - inicio);
            //System.out.println("Rodada " + (i + 1));
            writer.println("Rodada " + (i + 1));
            //System.out.println("Tempo: " + currTime + "ms");
            //writer.println("Tempo: " + currTime + "ms");
            totalTime += currTime;
        }

        long mean = totalTime / (long)times;
       
        //System.out.println("Tempo médio: " + mean + "ms");
        writer.println("Tempo médio: " + mean + "ms");
        //System.out.println();
        count++;
        writer.close();
        return new BenchmarkResult(totalTime, mean);
    }

}
