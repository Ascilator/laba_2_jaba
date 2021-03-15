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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

public class XmlReader extends Reader {
    public XmlReader() {
        songs = new ArrayList<Song>();
    }
    @Override
    public ArrayList<Song> Read(String filename, String filename_2) throws IOException, SAXException, ParserConfigurationException {
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

                Single songItem = new Single(name, singer, duration, placeInChart, studio);
                //System.out.println(songItem.Log());
                this.songs.add(songItem);
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

                Live songItem = new Live(name, singer, duration, placeInChart, date, place);
                //System.out.println(songItem.Log());
                this.songs.add(songItem);
                //System.out.println(this.songs.get(0).Log());
            }

        }

        return this.songs;
    }

}
