package com.epam.lab;

import com.epam.lab.model.Beer;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser {
    public List<Beer> parse(String fileName) {
        List<Beer> beers = new ArrayList<>();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            BeerHandler myBeerHandler = new BeerHandler();
            reader.setContentHandler(myBeerHandler);
            reader.parse(fileName);
            beers = myBeerHandler.getBeers();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return beers;
    }

}






