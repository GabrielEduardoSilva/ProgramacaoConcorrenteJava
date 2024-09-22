public class TemperaturaDiaria {
    private String pais;
    private String cidade;
    private int mes;
    private int dia;
    private int ano;
    private double temperatura;

    public TemperaturaDiaria(String pais, String cidade, int mes, int dia, int ano, double temperatura) {
        this.pais = pais;
        this.cidade = cidade;
        this.mes = mes;
        this.dia = dia;
        this.ano = ano;
        this.temperatura = temperatura;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public double getTemperatura() {
        return temperatura;
    }
}
