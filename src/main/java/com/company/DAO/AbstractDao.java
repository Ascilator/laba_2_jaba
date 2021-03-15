package com.company.DAO;

import com.company.file.UpdateFile;
import com.company.song.Song;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public abstract class AbstractDao<T> {
    public ArrayList<T> read(String filename, String filename_2) throws ParserConfigurationException, IOException, SAXException, ParseException {
        return null;
    }
    public void write(ArrayList<T> array) throws IOException {

    }
    public void update() throws IOException {

        Date date = new Date();
        UpdateFile fileUpdate = new UpdateFile();
        fileUpdate.appendUsingOutputStream("log.txt","used method write on:" + date.toString() + "\n");


    }
    public void update_reader() throws IOException {
        Date date = new Date();
        UpdateFile fileUpdate = new UpdateFile();
        fileUpdate.appendUsingOutputStream("log.txt","used method read on:" + date.toString() + "\n");
    }

}
