package com.example.geolocation.data.net.model;

public class WeatherObject
{
    private String id;

    private String dt;

    private Clouds clouds;

    private Coord coord;

    private Wind wind;

    private String cod;

    private Sys sys;

    private String name;

    private Weather[] weather;

    private Rain rain;

    private Main main;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public Clouds getClouds ()
    {
        return clouds;
    }

    public void setClouds (Clouds clouds)
    {
        this.clouds = clouds;
    }

    public Coord getCoord ()
    {
        return coord;
    }

    public void setCoord (Coord coord)
    {
        this.coord = coord;
    }

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    public String getCod ()
    {
        return cod;
    }

    public void setCod (String cod)
    {
        this.cod = cod;
    }

    public Sys getSys ()
    {
        return sys;
    }

    public void setSys (Sys sys)
    {
        this.sys = sys;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public Rain getRain ()
    {
        return rain;
    }

    public void setRain (Rain rain)
    {
        this.rain = rain;
    }

    public Main getMain()
    {
        return main;
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", dt = "+dt+", clouds = "+clouds+", coord = "+coord+", wind = "+wind+", cod = "+cod+", sys = "+sys+", name = "+name+", weather = "+weather+", rain = "+rain+", main = "+ main +"]";
    }

    public String getWeatherWithoutSystemInfo(){
        StringBuilder builder = new StringBuilder();
        for(Weather w: weather){
            builder.append(w.toString()).append("\n");
        }
        builder.append(main.toString()).append("\n");
        builder.append(wind.toString()).append("\n");
        builder.append(clouds.toString()).append("\n");
        return builder.toString();
    }

    public String getTotalWeather(){
        if(weather.length > 0){
            return weather[0].getDescription();
        }
        return null;
    }
}
