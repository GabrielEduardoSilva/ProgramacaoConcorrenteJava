import java.util.*;

public class CidadeDataPiece {
    private String country;
    private String city;
    private int day;
    private int month;
    private int year;
    private double temperature;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public CidadeDataPiece(String country, String city, int day, int month, int year, double temperature) {
        this.country = country;
        this.city = city;
        this.day = day;
        this.month = month;
        this.year = year;
        this.temperature = temperature;
    }


}
