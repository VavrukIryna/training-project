package com.epam.lab;

import com.epam.lab.model.*;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    public List<Beer> parse(String fileName) {
        List<Beer> beers = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {

            Reader fileReader = new FileReader(fileName);
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(fileReader);

            // Model
            Chars chars = null;
            Ingredients ingredients = null;;
            SpillMethod spillMethod = null;
            Beer beer = null;

            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equalsIgnoreCase("beer")) {
                        // Initialization
                        chars = new Chars();
                        ingredients = new Ingredients();
                        spillMethod = new SpillMethod();
                        beer = new Beer();
                        beer.setIngredients(ingredients);
                        chars.setSpillMethod(spillMethod);
                        beer.setChars(chars);
                    }

                    if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        beer.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("type")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        beer.setType(Type.valueOf(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("ai")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        beer.setAi(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("manufacturer")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        beer.setManufacturer(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("water")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        ingredients.setWater(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("malt")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        ingredients.setMalt(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("hop")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        ingredients.setHop(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("sugar")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        ingredients.setSugar(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("volumeAlcoholFraction")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        chars.setVolumeAlcoholFraction(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("clearness")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        chars.setClearness(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("filtered")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        chars.setFiltered(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("nutritionalValue")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        chars.setNutritionalValue(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("material")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        spillMethod.setMatherial(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("size")) {
                        xmlEvent = xmlEventReader.nextEvent();
                        spillMethod.setSize(Double.parseDouble(xmlEvent.asCharacters().getData()));
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("beer")) {
                        beers.add(beer);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return beers;
    }
}
