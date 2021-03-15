package com.company.reader;

import com.company.song.Live;
import com.company.song.Single;
import com.company.song.Song;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader extends Reader {
    public JsonReader() {
        songs = new ArrayList<>();
    }

    @Override
    public ArrayList<Song> Read(String filename, String filename_2) throws IOException, SAXException, ParserConfigurationException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader(filename));

        for (Object o : a) {
            JSONObject songItemJson = (JSONObject) o;

            String name = (String) songItemJson.get("name");
            //System.out.println(name);

            String singer = (String) songItemJson.get("singer");
            //.out.println(singer);

            Integer duration =  Integer.parseInt(String.valueOf(songItemJson.get("duration")));
            //System.out.println(duration);

            Integer place =  Integer.parseInt(String.valueOf(songItemJson.get("placeInChart")));
            //System.out.println(place);

            String studio =  String.valueOf(songItemJson.get("studio"));
            Single songItem = new Single(name, singer, duration, place, studio);


            //System.out.println(songItem.Log());
            this.songs.add(songItem);

        }
        JSONParser parser_2 = new JSONParser();
        JSONArray a_2 = (JSONArray) parser_2.parse(new FileReader(filename_2));

        for (Object o : a_2) {
            JSONObject songItemJson = (JSONObject) o;

            String name = (String) songItemJson.get("name");
            //System.out.println(name);

            String singer = (String) songItemJson.get("singer");
            //.out.println(singer);

            Integer duration =  Integer.parseInt(String.valueOf(songItemJson.get("duration")));
            //System.out.println(duration);

            Integer place =  Integer.parseInt(String.valueOf(songItemJson.get("placeInChart")));
            //System.out.println(place);

            String date = (String) songItemJson.get("date");
            String townPlace = (String) songItemJson.get("date");

            Live songItem = new Live(name, singer, duration, place, date, townPlace);


            //System.out.println(songItem.Log());
            this.songs.add(songItem);

        }
        return this.songs;
    }
}
