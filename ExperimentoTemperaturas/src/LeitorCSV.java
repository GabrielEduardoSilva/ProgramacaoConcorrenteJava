import java.io.*;
import java.nio.file.*;
import java.util.*;

public class LeitorCSV {
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
