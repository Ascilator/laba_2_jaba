package com.company.song;

public class Live extends Song {
    public String date;
    public String place;

    public Live(String songName, String singer, int duration, int placeInChart, String date, String place) {
        super(songName, singer, duration, placeInChart);
        this.date = date;
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }
    public String Log() {
        String secs;
        if (this.duration % 60 < 10) {
            secs = "0" + this.duration % 60;
        } else {
            secs = String.valueOf(this.duration % 60);
        }
        String pos;
        if (this.placeInChart == 1000) {
            pos = "не входит в топ.";
        } else {
            pos = "place in chart #" + String.valueOf(this.placeInChart);
        }
        return ("Single:\n" + this.singer + " - " + this.songName + "\n\t" + this.duration / 60 + ":" + secs + ", " + pos + "\nДата выхода:"+ this.date +"\n Место: " + this.place + "\n");

    }
}
