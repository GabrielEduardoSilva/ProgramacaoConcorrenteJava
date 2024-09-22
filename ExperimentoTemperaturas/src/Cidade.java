import java.util.*;

public class Cidade {
    private String nome;
    private List<TemperaturaDiaria> dadosTemperatura;

    public Cidade(String nome, List<TemperaturaDiaria> dadosTemperatura) {
        this.nome = nome;
        this.dadosTemperatura = dadosTemperatura;
    }

    public Map<Integer, CalculosTemperatura> calcularTemperaturasMensais() {
        Map<Integer, CalculosTemperatura> resultadoMensal = new HashMap<>();

        for (TemperaturaDiaria temp : dadosTemperatura) {
            int chaveMes = temp.getAno() * 100 + temp.getMes();  // Identifica o mês de um determinado ano
            CalculosTemperatura res = resultadoMensal.getOrDefault(chaveMes, new CalculosTemperatura());

            res.adicionarTemperatura(temp.getTemperatura());
            resultadoMensal.put(chaveMes, res);
        }

        return resultadoMensal;
    }

    public String getNome() {
        return nome;
    }

    public List<TemperaturaDiaria> getDadosTemperatura() {
        return dadosTemperatura;
    }
}
