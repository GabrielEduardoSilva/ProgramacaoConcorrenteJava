import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String diretorioPath = "src/temperaturas_cidades";
        // Carregar dados das cidades

        List<Path> paths = LeitorCSV.carregarPaths(diretorioPath);

        int[] experiments = {
            2,4,8,16,32,64,80,160,320
        };

        // Experiment 1
        BenchmarkResult benchmarkResult1 = Benchmarker.benchmark(() -> {
            ArrayList<Thread> threads = new ArrayList<>();
            
            for (Path path: paths) {
                String nomeCidade = path.getFileName().toString().replace(".csv", "");
                CidadeData cidades = LeitorCSV.lerDadosCidade(path, 1);
            }

            return null;
        });


        // Experiment 2-10
        for (int threadNum: experiments) {
           BenchmarkResult benchmarkResult2 = Benchmarker.benchmark(() -> {
               ArrayList<Thread> threads = new ArrayList<>();
               int parte = paths.size() / threadNum;
               for (int i = 0; i < threadNum; i++) {
                   int inicioT = i * parte;
                   int fimT = Math.min(inicioT + parte, paths.size());
                   Thread thread = new Thread(new Runnable() {
                       @Override
                       public void run() {
                           for (int j = inicioT; j < fimT; j++) {
                               Path path = paths.get(j);
                               String nomeCidade = path.getFileName().toString().replace(".csv", "");
                               CidadeData cidadeData = LeitorCSV.lerDadosCidade(path, 1);
                           }
                       }
                   });

                   threads.add(thread);
                   thread.start();
               }
               // Aguarda a conclusão de todas as tarefas
               for (Thread thread : threads) {
                   thread.join(); // Garante que cada tarefa foi concluída
               }
               return null;
           });



        }

        ArrayList<CidadeData> cidadeData = new ArrayList<>();

        // Experiment 11
        BenchmarkResult benchmarkResult2 = Benchmarker.benchmark(() -> {
            for (Path path: paths) {
                String nomeCidade = path.getFileName().toString().replace(".csv", "");
                CidadeData cidades = LeitorCSV.lerDadosCidade(path, 1);
                cidadeData.add(cidades);
            }

            return null;
        });



        // Experiment 12-20
        for (int threadNum: experiments) {
            BenchmarkResult benchmarkResult3 = Benchmarker.benchmark(() -> {
                ArrayList<Thread> threads = new ArrayList<>();
                int parte = paths.size() / threadNum;
                for (int i = 0; i < threadNum; i++) {
                    int inicioT = i * parte;
                    int fimT = Math.min(inicioT + parte, paths.size());
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = inicioT; j < fimT; j++) {
                                Path path = paths.get(j);
                                String nomeCidade = path.getFileName().toString().replace(".csv", "");
                                CidadeData data = LeitorCSV.lerDadosCidade(path, 26);
                            }
                        }
                    });

                    threads.add(thread);
                    thread.start();
                }
                // Aguarda a conclusão de todas as tarefas
                for (Thread thread : threads) {
                    thread.join(); // Garante que cada tarefa foi concluída
                }
                return null;
            });



        }
    }
}