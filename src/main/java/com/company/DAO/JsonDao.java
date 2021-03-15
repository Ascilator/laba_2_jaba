package com.company.DAO;

import com.company.song.Live;
import com.company.song.Single;
import com.company.song.Song;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonDao<T> extends AbstractDao<T>{
    @Override
    public ArrayList<T> read(String filename, String filename_2) throws IOException, ParseException {
        ArrayList<T> songsArray = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader(filename));

        for (Object o : a) {
            JSONObject songItemJson = (JSONObject) o;

            String name = (String) songItemJson.get("name");
            //System.out.println(name);

            String singer = (String) songItemJson.get("singer");
            //.out.println(singer);

            Integer duration = Integer.parseInt(String.valueOf(songItemJson.get("duration")));
            //System.out.println(duration);

            Integer place = Integer.parseInt(String.valueOf(songItemJson.get("placeInChart")));
            //System.out.println(place);

            String studio = String.valueOf(songItemJson.get("studio"));
            Single songItem = new Single(name, singer, duration, place, studio);


            //System.out.println(songItem.Log());
            songsArray.add((T) songItem);
        }
        JSONParser parser_2 = new JSONParser();
        JSONArray a_2 = (JSONArray) parser_2.parse(new FileReader(filename_2));

        for (Object o : a_2) {
            JSONObject songItemJson = (JSONObject) o;

            String name = (String) songItemJson.get("name");
            //System.out.println(name);

            String singer = (String) songItemJson.get("singer");
            //.out.println(singer);

            Integer duration = Integer.parseInt(String.valueOf(songItemJson.get("duration")));
            //System.out.println(duration);

            Integer place = Integer.parseInt(String.valueOf(songItemJson.get("placeInChart")));
            //System.out.println(place);

            String date = (String) songItemJson.get("date");
            String townPlace = (String) songItemJson.get("date");

            Live songItem = new Live(name, singer, duration, place, date, townPlace);


            //System.out.println(songItem.Log());
            songsArray.add((T) songItem);

        }
        update_reader();
        return songsArray;
    }
    @Override
    public void write(ArrayList<T> array) throws IOException {
        JSONArray listMain = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = new JSONObject();


            if(array.get(i) instanceof Single) {
                Single song = (Single) array.get(i);
                obj.put("name", song.getSongName());
                obj.put("singer", song.getSinger());
                obj.put("duration", song.getDuration());
                obj.put("placeInChart", song.getPlaceInChart());
                obj.put("studio", song.getStudio());
            } else {
                Live song = (Live) array.get(i);
                obj.put("name", song.getSongName());
                obj.put("singer", song.getSinger());
                obj.put("duration", song.getDuration());
                obj.put("placeInChart", song.getPlaceInChart());
                obj.put("date", song.getDate());
                obj.put("place", song.getPlace());
            }
            listMain.add(obj);
        }

        try {

            try (FileWriter file = new FileWriter("result.json")) {
                file.write(listMain.toJSONString());
                file.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        update();

    }
}
