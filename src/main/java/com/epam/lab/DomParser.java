package com.epam.lab;

import com.epam.lab.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    public List<Beer> parse(String fileName) {
        List<Beer> beers = new ArrayList<>();
        try {
            DocumentBuilderFactory dbfact = DocumentBuilderFactory.newInstance();
            dbfact.setNamespaceAware(true);
            DocumentBuilder dB = dbfact.newDocumentBuilder();
            Document doc = dB.parse(fileName);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Beer");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("Current Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Beer newBeer = new Beer();
                    Ingredients newIngredients = new Ingredients();
                    Chars newChars = new Chars();
                    SpillMethod newSpillMethod = new SpillMethod();
                    newBeer.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    newBeer.setType(Type.valueOf(eElement.getElementsByTagName("type").item(0).getTextContent()));
                    newBeer.setAi(eElement.getElementsByTagName("ai").item(0).getTextContent());
                    newBeer.setManufacturer(eElement.getElementsByTagName("manufacturer").item(0).getTextContent());
                    newIngredients.setWater(new Integer(eElement.getElementsByTagName("water").item(0).getTextContent()));
                    newIngredients.setMalt(new Integer(eElement.getElementsByTagName("malt").item(0).getTextContent()));
                    newIngredients.setHop(new Integer(eElement.getElementsByTagName("hop").item(0).getTextContent()));
                    newIngredients.setSugar(new Integer(eElement.getElementsByTagName("sugar").item(0).getTextContent()));
                    newBeer.setIngredients(newIngredients);
                    newChars.setVolumeAlcoholFraction(new Double(eElement.getElementsByTagName("volumeAlcoholFraction").item(0).getTextContent()));
                    newChars.setClearness(eElement.getElementsByTagName("clearness").item(0).getTextContent());
                    newChars.setFiltered(eElement.getElementsByTagName("filtered").item(0).getTextContent());
                    newChars.setNutritionalValue(eElement.getElementsByTagName("nutritionalValue").item(0).getTextContent());
                    newSpillMethod.setMatherial(eElement.getElementsByTagName("material").item(0).getTextContent());
                    newSpillMethod.setSize(new Double(eElement.getElementsByTagName("size").item(0).getTextContent()));
                    newChars.setSpillMethod(newSpillMethod);
                    newBeer.setChars(newChars);
                    beers.add(newBeer);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beers;
    }
}
