import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeitorCSV {

    // Lê os arquivos CSV do diretório diretorioPath
    // Chama a função lerDadosCidade e armazena em uma lista
    // Armazena os dados de cada cidade em um Map (a chave é o nome da cidade).
    // Ao final, transforma esse mapa em uma lista de objetos Cidade.

    public static ArrayList<Path> carregarPaths(String diretorioPath) {
        ArrayList<Path> arr = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(diretorioPath), "*.csv")) {



            for (Path filePath : stream) {
                arr.add(filePath);
            }


        } catch (Exception e) {
            System.err.println("Erro ao processar os arquivos CSV: " + e.getMessage());
        }

        return arr;
    }


    public static List<CidadeDataPiece> carregarDadosCidades(String diretorioPath, int numThreads) {
        long inicio = System.currentTimeMillis();
        List<CidadeDataPiece> cidadeDataPieces = new ArrayList<>();
        Map<String, CidadeData> dadosCidades = new ConcurrentHashMap<>(); // Map thread-safe
        ExecutorService executor = Executors.newFixedThreadPool(numThreads); // Pool de threads
        List<Future<Void>> futures = new ArrayList<>(); // Lista para acompanhar as tarefas
    List<Thread> threads  = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();


        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(diretorioPath), "*.csv")) {

            List<Path> paths = new ArrayList<>();
            stream.forEach(paths::add);
            int parte = paths.size() / numThreads;
            for (int i = 0; i < numThreads; i++) {
                int inicioT = i * parte;
                int fimT = Math.min(inicioT + parte, paths.size());
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = inicioT; j < fimT; j++) {
                                Path path = paths.get(j);
                                String nomeCidade = path.getFileName().toString().replace(".csv", "");
                                CidadeData cidadeDataPiece = lerDadosCidade(path, 1);
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


        } catch (Exception e) {
            System.err.println("Erro ao processar os arquivos CSV: " + e.getMessage());
        } finally {
            executor.shutdown(); // Finaliza o pool de threads
        }

        long fim = System.currentTimeMillis();
        long tempoTotal = fim - inicio; // Calcula a diferença entre o início e o fim
        // Exibe o tempo gasto
        System.out.println("Tempo total para processar os arquivos: " + tempoTotal + " ms");
        System.out.println("Número de Threads: " + numThreads);
        return cidadeDataPieces;
    }

    // Essa função lê os arquivos CSV e adiciona em uma lista de TemperaturaDiaria
    static CidadeData lerDadosCidade(Path filePath, int numThreads) {

        ArrayList<Thread> threads = new ArrayList<>();
        List<CidadeDataPiece> pieces = Collections.synchronizedList(new ArrayList<>());
        CidadeData data = new CidadeData();
        try {
            List<String> lines = Files.readAllLines(filePath);
            lines.remove(0);

            int lineCount = lines.size();

            int parte = lineCount / numThreads;
 
            for (int i = 0; i < numThreads; i++) {
                int inicioT = i * parte;
                int finalT = Math.min(lineCount, inicioT + parte);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                    	
                        for (int j = inicioT; j < finalT; j++) {
                        	
                            String linha = lines.get(j);
                            String[] valores = linha.split(",");
                            String pais = valores[0];
                            String nome = valores[1];
                            int mes = Integer.parseInt(valores[2]);
                            int dia = Integer.parseInt(valores[3]);
                            int ano = Integer.parseInt(valores[4]);
                            double temperatura = Double.parseDouble(valores[5]);

                            CidadeDataPiece cidadeDataPiece = new CidadeDataPiece(nome, pais, dia, mes, ano, temperatura);
                        	
                        	
                            synchronized (data) {
                            	data.dataPieces.add(cidadeDataPiece);
                            }
                        }
                    }
                });

                threads.add(thread);
                thread.start();

            }

            for (Thread thread: threads) {
                thread.join();
            }
            
            
            //System.out.println(pieces.size());
           data.print();

            

            return data;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
