package com.epam.lab;

import com.epam.lab.model.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

class BeerHandler extends DefaultHandler {
    List<Beer> beers = new ArrayList<>();
    String fieldName;
    Beer newBeer = new Beer();
    Ingredients ingredients = new Ingredients();
    Chars chars = new Chars();
    SpillMethod spillMethod = new SpillMethod();

    public List<Beer> getBeers() {
        return beers;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML.");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parse XML.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        fieldName = qName;
        if (qName.equalsIgnoreCase("Beer")) {

            beers.add(newBeer);
        }
    }


    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        fieldName = "";
        if (qName.equalsIgnoreCase("Beer")) {
            newBeer = new Beer();
            System.out.println("End Element :" + qName);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        String item = new String(ch, start, length);
        if (fieldName.equalsIgnoreCase("name")) {
            newBeer.setName(item);
        } else if (fieldName.equalsIgnoreCase("type")) {
            newBeer.setType(Type.valueOf(item));
        } else if (fieldName.equalsIgnoreCase("ai")) {
            newBeer.setAi(item);
        } else if (fieldName.equalsIgnoreCase("manufacturer")) {
            newBeer.setManufacturer(item);
        } else if (fieldName.equalsIgnoreCase("water")) {
            ingredients.setWater(Integer.parseInt(item));
        } else if (fieldName.equalsIgnoreCase("malt")) {
            ingredients.setMalt(Integer.parseInt(item));
        } else if (fieldName.equalsIgnoreCase("hop")) {
            ingredients.setHop(Integer.parseInt(item));
        } else if (fieldName.equalsIgnoreCase("sugar")) {
            ingredients.setSugar(Integer.parseInt(item));
        }
        newBeer.setIngredients(ingredients);
        if (fieldName.equalsIgnoreCase("volumeAlcoholFraction")) {
            chars.setVolumeAlcoholFraction(Double.parseDouble(item));
        } else if (fieldName.equalsIgnoreCase("clearness")) {
            chars.setClearness(item);
        } else if (fieldName.equalsIgnoreCase("filtered")) {
            chars.setFiltered(item);
        } else if (fieldName.equalsIgnoreCase("nutritionalValue")) {
            chars.setNutritionalValue(item);
        } else if (fieldName.equalsIgnoreCase("material")) {
            spillMethod.setMatherial(item);
        } else if (fieldName.equalsIgnoreCase("size")) {
            spillMethod.setSize(Double.parseDouble(item));
        }
        chars.setSpillMethod(spillMethod);
        newBeer.setChars(chars);
    }
}

