package com.company.reader;

import com.company.song.Live;
import com.company.song.Single;
import com.company.song.Song;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public abstract class Reader {
    protected static ArrayList<Song> songs;


    public Reader() {
        songs = new ArrayList<Song>();
    }

    public ArrayList<Song> Read(String filename, String filename_2) throws IOException, SAXException, ParserConfigurationException, ParseException {
        return new ArrayList<Song>();
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }



    public static void printSongs() {
        for(int i = 0; i < songs.size(); i++) {
            System.out.println(songs.get(i).Log_2());
        }
    }
    /*
    public static void printSongs_2() {
        for(int i = 0; i < songs.size(); i++) {
            System.out.println(songs.get(i).Log_2());
        }
    }*/

    //    Collections.sort(Songs);

    public static void sortByChart() {
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if(s1.getPlaceInChart() > s2.getPlaceInChart()) {
                    return 1;
                }
                if(s1.getPlaceInChart() < s2.getPlaceInChart()) {
                    return -1;
                }
                if(s1.getPlaceInChart() == s2.getPlaceInChart()) {
                    if(s1.getSinger().compareTo(s2.getSinger()) > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
                return 0;
            }
        });


        printSongs();
    }



    public static int averageDur() {
        int summ = 0;
        for(int i = 0; i < songs.size(); i++) {
            summ += songs.get(i).getDuration();
        }
        String secs;
        if (summ/songs.size() % 60 < 10) {
            secs = "0" + summ/songs.size() % 60;
        } else {
            secs = String.valueOf(summ/songs.size() % 60);
        }
        System.out.println(summ/songs.size()/60 + ":" + secs);
        return summ/songs.size()/60;
    }

    public static int allDur() {
        int summ = 0;
        for(int i = 0; i < songs.size(); i++) {
            summ += songs.get(i).getDuration();
        }
        String secs;
        if (summ % 60 < 10) {
            secs = "0" + summ % 60;
        } else {
            secs = String.valueOf(summ % 60);
        }
        System.out.println(summ/60 + ":" +secs);
        return (summ/60);
    }

    public static void soutSinger() {
        System.out.println("Пожалуйста введите имя исполнителя");
        Scanner scanner = new Scanner(System.in);
        String singer = scanner.nextLine();
        boolean yosNo = false;
        for(int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSinger().compareTo(singer) == 0) {
                yosNo = true;
                System.out.println(songs.get(i).Log());
            }
        }
        if (!yosNo) {
            System.out.println("По вашему запросу ничего не найдено :( \n");
        }
    }

    public static void thanks() {
        System.out.println("(⌒‿⌒)");
    }


}
