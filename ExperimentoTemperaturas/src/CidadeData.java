import java.util.ArrayList;

public class CidadeData {

    ArrayList<Integer> months = new ArrayList<>();
    int monthCount = 0;
    public ArrayList<CidadeDataPiece> dataPieces = new ArrayList<>();

    public void addData(CidadeDataPiece piece) {
        dataPieces.add(piece);
    }

    public TemperatureData[] temperaturePerMonth() {

        TemperatureData[] temperatures = {
          new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
                new TemperatureData(10000, -10000, 0),
        };

        int count = 0;

        for (CidadeDataPiece piece: dataPieces) {
            count++;
            int month = piece.getMonth()- 1;
            double temperature = piece.getTemperature();

            temperatures[month].min = Math.min(temperatures[month].min, temperature);
            temperatures[month].max = Math.max(temperatures[month].max, temperature);
            temperatures[month].mean = temperature;
        }

        for (TemperatureData temp: temperatures) {
            temp.mean = temp.mean / count;
        }

        return temperatures;
    }

    public int countMonths() {

        ArrayList<Integer> months = new ArrayList<>();

        for (CidadeDataPiece piece: dataPieces) {
            if (!months.contains(piece.getMonth())) {
                months.add(piece.getMonth());
            }
        }

        return months.size();

    }

    public CidadeData() {}

}
