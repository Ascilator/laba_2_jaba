package com.company.DAO;

import au.com.bytecode.opencsv.CSVReader;
import com.company.song.Live;
import com.company.song.Single;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

import static java.util.regex.Pattern.matches;

public class CsvDao <T> extends AbstractDao<T>{
    @Override
    public ArrayList<T> read(String filename, String filename_2) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filename), ',' , '"' , 0);
        ArrayList<T> songsArray = new ArrayList<>();

        //Read CSV line by line and use the string array as you want
        String[] nextLine;

        while ((nextLine = reader.readNext()) != null) {

            //System.out.println(Arrays.toString(nextLine));

            String name = (String) nextLine[0];
            //System.out.println(name);

            String singer = (String) nextLine[1];
            //System.out.println(singer);

            Integer duration =  Integer.parseInt(nextLine[2]);
            //System.out.println(duration);

            Integer place =  Integer.parseInt(nextLine[3]);
            //System.out.println(place);
            String studio = (String) nextLine[4];
            //System.out.println(singer);
            if(matches( "^[0-9]+$", place.toString()) == false) {
                System.out.println("Место в чате не прошло валидацию");
            }
            if(matches( "^[0-9]+$", duration.toString()) == false) {
                System.out.println("Длительность не прошла валидацию");
            }
            Single songItem = new Single(name, singer, duration, place, studio);

            //System.out.println(songItem.Log());
            songsArray.add((T)songItem);


        }

        CSVReader reader_2 = new CSVReader(new FileReader(filename_2), ',' , '"' , 0);


        //Read CSV line by line and use the string array as you want
        String[] nextLine_2;

        while ((nextLine_2 = reader_2.readNext()) != null) {

            //System.out.println(Arrays.toString(nextLine));

            String name = (String) nextLine_2[0];
            //System.out.println(name);

            String singer = (String) nextLine_2[1];
            //System.out.println(singer);

            Integer duration =  Integer.parseInt(nextLine_2[2]);
            //System.out.println(duration);

            Integer place =  Integer.parseInt(nextLine_2[3]);
            //System.out.println(place);
            String date = (String) nextLine_2[4];
            String townPlace = (String) nextLine_2[5];
            if(matches( "^[0-9]+$", place.toString()) == false) {
                System.out.println("Место в чате не прошло валидацию");
            }
            if(matches( "^[0-9]+$", duration.toString()) == false) {
                System.out.println("Место в чате не прошло валидацию");
            }
            if(matches( "([1-9]|[1-3][0-9])\\s[а-я]+\\s([0-1][0-9][0-9][0-9]|[2][0][0-2][0-9])\\s[а-я]+", date) == false) {
                System.out.println("Год не прошло валидацию");
            }
            Live songItem = new Live(name, singer, duration, place, date, townPlace);

            //System.out.println(songItem.Log());
            songsArray.add((T)songItem);
        }


        update_reader();
        return songsArray;
    }

    @Override
    public void write(ArrayList<T> array) throws IOException {
        File file = new File("result.csv");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        for (int i = 0; i < array.size(); i++) {



            if(array.get(i) instanceof Single) {
                Single song = (Single) array.get(i);
                writer.write( "\"" + song.getSongName()+ "\"," + "\"" + song.getSinger() + "\"," + song.getDuration() + "," + song.getPlaceInChart() + "," + "\"" + song.getStudio() + "\"\n");
                writer.flush();
            } else {
                Live song = (Live) array.get(i);
                writer.write( "\"" + song.getSongName()+ "\"," + "\"" + song.getSinger() + "\"," + song.getDuration() + "," + song.getPlaceInChart() + "," + "\"" + song.getDate() + "\",\"" + song.getPlace() + "\"\n");
                writer.flush();
            }
        }

        writer.close();
        update();
    }
}
