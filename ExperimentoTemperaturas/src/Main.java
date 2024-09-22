import java.util.*;

public class Main {
    public static void main(String[] args) {
        String diretorioPath = "src/temperaturas_cidades";

        // Carregar dados das cidades
        List<Cidade> cidades = LeitorCSV.carregarDadosCidades(diretorioPath);

        // Exibir as cidades carregadas
        for (Cidade cidade : cidades) {
            System.out.println("Cidade: " + cidade.getNome() + " - Total de registros: " + cidade.getDadosTemperatura().size());
        }
    }
}