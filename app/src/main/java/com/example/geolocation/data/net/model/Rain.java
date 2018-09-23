package com.example.geolocation.data.net.model;

public class Rain
{
    private String time;

    public String getTime()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [time = "+time+"]";
    }
}
