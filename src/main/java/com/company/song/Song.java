package com.company.song;

public abstract class Song {
    protected String songName;
    protected String singer;
    protected int duration;
    protected int placeInChart;

    public Song(String songName, String singer, int duration, int placeInChart) {
        this.songName = songName;
        this.singer = singer;
        this.duration = duration;
        this.placeInChart = placeInChart;
    }
    public abstract String Log();
    public String Log_2() {
        String pos;
        if (this.placeInChart == 1000) {
            pos = "не входит в топ.";
        } else {
            pos = "place in chart #" + String.valueOf(this.placeInChart);
        }
        return (this.singer + " - " + this.songName + "\t--\t" + " " + pos);
    }


    public String getSongName() {
        return songName;
    }

    public String getSinger() {
        return singer;
    }

    public int getDuration() {
        return duration;
    }

    public int getPlaceInChart() {
        return placeInChart;
    }


}
