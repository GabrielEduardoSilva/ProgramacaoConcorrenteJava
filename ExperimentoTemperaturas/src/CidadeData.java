import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CidadeData {

    ArrayList<Integer> months = new ArrayList<>();
    int monthCount = 0;
    public List<CidadeDataPiece> dataPieces = new ArrayList<>();
    
  
    
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
    
    public void print() {
    	System.out.println("Cidade: " + dataPieces.get(0).getCountry() + "-" + dataPieces.get(0).getCity());
    	System.out.println("Mes | Temp Min | Temp Max | Temp Media");
    	
    	TemperatureData[] temperaturas = temperaturePerMonth();
    	
    	int index = 0;
    	for (TemperatureData temp: temperaturas) {
    		String result = "";
    		
    		
    		System.out.format("%d | %f | %f | %f\n", index + 1, temp.min, temp.max, temp.mean);
    		index++;
    	}
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


}
