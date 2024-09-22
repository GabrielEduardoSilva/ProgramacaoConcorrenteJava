import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LeitorCSV {

    // Lê os arquivos CSV do diretório diretorioPath
    // Chama a função lerDadosCidade e armazena em uma lista
    // Armazena os dados de cada cidade em um Map (a chave é o nome da cidade).
    // Ao final, transforma esse mapa em uma lista de objetos Cidade.
    public static List<Cidade> carregarDadosCidades(String diretorioPath) {
        List<Cidade> cidades = new ArrayList<>();
        Map<String, List<TemperaturaDiaria>> dadosCidades = new HashMap<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(diretorioPath), "*.csv")) {
            for (Path filePath : stream) {
                // Lê o nome da cidade a partir do nome do arquivo
                String nomeCidade = filePath.getFileName().toString().replace(".csv", "");

                List<TemperaturaDiaria> temperaturas = lerDadosCidade(filePath);
                dadosCidades.put(nomeCidade, temperaturas);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler os arquivos CSV: " + e.getMessage());
        }

        // Transforma o mapa de cidades e seus dados em objetos Cidade
        for (Map.Entry<String, List<TemperaturaDiaria>> entry : dadosCidades.entrySet()) {
            Cidade cidade = new Cidade(entry.getKey(), entry.getValue());
            cidades.add(cidade);
        }

        return cidades;
    }

    // Essa função lê os arquivos CSV e adiciona em uma lista de TemperaturaDiaria
    private static List<TemperaturaDiaria> lerDadosCidade(Path filePath) {
        List<TemperaturaDiaria> temperaturas = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String linha;
            br.readLine(); // Ignora o cabeçalho

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(",");
                String pais = valores[0];
                String cidade = valores[1];
                int mes = Integer.parseInt(valores[2]);
                int dia = Integer.parseInt(valores[3]);
                int ano = Integer.parseInt(valores[4]);
                double temperatura = Double.parseDouble(valores[5]);

                TemperaturaDiaria temp = new TemperaturaDiaria(pais, cidade, mes, dia, ano, temperatura);
                temperaturas.add(temp);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return temperaturas;
    }
}
