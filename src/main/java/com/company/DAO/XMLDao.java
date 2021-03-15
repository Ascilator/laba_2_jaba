package com.company.DAO;

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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.regex.Pattern.matches;

public class XMLDao<T> extends AbstractDao<T>{
    @Override
    public ArrayList<T> read(String filename, String filename_2) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<T> songsArray = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(filename));
        Element element =  document.getDocumentElement();
        NodeList songs = element.getChildNodes();

        for (int i = 0; i < songs.getLength(); i++){
            if (songs.item(i).hasChildNodes()){

                String name  = songs.item(i).getChildNodes().item(1).getTextContent();
                String singer = songs.item(i).getChildNodes().item(3).getTextContent();
                Integer duration  = Integer.parseInt(songs.item(i).getChildNodes().item(5).getTextContent());
                Integer placeInChart = Integer.parseInt(songs.item(i).getChildNodes().item(7).getTextContent());
                String studio = songs.item(i).getChildNodes().item(9).getTextContent();



                if(matches( "^[0-9]+$", placeInChart.toString()) == false) {
                    System.out.println("Место в чате не прошло валидацию");
                }
                if(matches( "^[0-9]+$", duration.toString()) == false) {
                    System.out.println("Длительность не прошла валидацию");
                }



                Single songItem = new Single(name, singer, duration, placeInChart, studio);
                //System.out.println(songItem.Log());
                songsArray.add((T) songItem);
                //System.out.println(this.songs.get(0).Log());
            }

        }
        DocumentBuilderFactory factory_2 = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder_2 = factory_2.newDocumentBuilder();
        Document document_2 = builder_2.parse(new File(filename_2));
        Element element_2 =  document_2.getDocumentElement();
        NodeList songs_2 = element_2.getChildNodes();

        for (int i = 0; i < songs_2.getLength(); i++){
            if (songs_2.item(i).hasChildNodes()){

                String name  = songs_2.item(i).getChildNodes().item(1).getTextContent();
                String singer = songs_2.item(i).getChildNodes().item(3).getTextContent();
                Integer duration  = Integer.parseInt(songs_2.item(i).getChildNodes().item(5).getTextContent());
                Integer placeInChart = Integer.parseInt(songs_2.item(i).getChildNodes().item(7).getTextContent());

                String date = songs_2.item(i).getChildNodes().item(9).getTextContent();
                String place = songs_2.item(i).getChildNodes().item(11).getTextContent();
                if(matches( "^[0-9]+$", duration.toString()) == false) {
                    System.out.println("Длительность не прошла валидацию");
                }

                if(matches( "([1-9]|[1-3][0-9])\\s[а-я]+\\s([0-1][0-9][0-9][0-9]|[2][0][0-2][0-9])\\s[а-я]+", date) == false) {
                    System.out.println("Год не прошло валидацию");
                }


                Live songItem = new Live(name, singer, duration, placeInChart, date, place);
                //System.out.println(songItem.Log());
                songsArray.add((T) songItem);
                //System.out.println(this.songs.get(0).Log());
            }

        }
        update_reader();
        return songsArray;
    }
    @Override
    public void write(ArrayList<T> array) throws IOException {
        File file = new File("result.xml");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<playlist>\n");

        for (int i = 0; i < array.size(); i++) {

            writer.write("\t<SongItem>\n");

            if(array.get(i) instanceof Single) {
                Single song = (Single) array.get(i);

                writer.write("\t\t<name>" + song.getSongName() + "</name>\n");
                writer.write("\t\t<singer>" + song.getSinger() + "</singer>\n");
                writer.write("\t\t<duration>" + song.getDuration() + "</duration>\n");
                writer.write("\t\t<placeInChart>" + song.getPlaceInChart() + "</placeInChart>\n");
                writer.write("\t\t<studio>" + song.getStudio() + "</studio>\n");

                writer.flush();
            } else {
                Live song = (Live) array.get(i);

                writer.write("\t\t<name>" + song.getSongName() + "</name>\n");
                writer.write("\t\t<singer>" + song.getSinger() + "</singer>\n");
                writer.write("\t\t<duration>" + song.getDuration() + "</duration>\n");
                writer.write("\t\t<placeInChart>" + song.getPlaceInChart() + "</placeInChart>\n");
                writer.write("\t\t<date>" + song.getDate() + "</date>\n");
                writer.write("\t\t<place>" + song.getPlace() + "</place>\n");
                writer.flush();
            }
            writer.write("\t</SongItem>\n");
        }
        writer.write("</playlist>");
        writer.close();
        update();
    }
}
