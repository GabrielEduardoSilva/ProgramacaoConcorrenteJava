public class CalculosTemperatura {
    private double somaTemperaturas;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private int contagem;

    public CalculosTemperatura() {
        this.somaTemperaturas = 0;
        this.temperaturaMaxima = Double.MIN_VALUE;
        this.temperaturaMinima = Double.MAX_VALUE;
        this.contagem = 0;
    }

    public void adicionarTemperatura(double temperatura) {
        somaTemperaturas += temperatura;
        if (temperatura > temperaturaMaxima) {
            temperaturaMaxima = temperatura;
        }
        if (temperatura < temperaturaMinima) {
            temperaturaMinima = temperatura;
        }
        contagem++;
    }

    public double calcularMedia() {
        return somaTemperaturas / contagem;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }
}
