package com.epam.lab;

import com.epam.lab.model.Beer;
import com.epam.lab.service.BeerComparator;
import com.epam.lab.service.BeerService;
import com.epam.lab.service.MySqlService;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scan;

    public static List<Beer> saxReadFromXML(String fileName) {
        List<Beer> beers = new ArrayList<>();
        System.out.println("______________________");
        System.out.println("SAX  Parser");
        SaxParser saxParser = new SaxParser();
        beers = saxParser.parse(fileName);
        for (Beer item : beers) {
            System.out.println(item.toString());
        }
        return beers;
    }

    public static List<Beer> domReadFromXML(String fileName) {
        List<Beer> beers = new ArrayList<>();
        System.out.println("______________________");
        System.out.println("DOM parser");
        DomParser domParser = new DomParser();
        beers = domParser.parse(fileName);
        for (Beer item : beers) {
            System.out.println(item.toString());
        }
        return beers;
    }

    public static List<Beer> staxReadFromXML(String fileName) {
        List<Beer> beers = new ArrayList<>();
        System.out.println("______________________");
        System.out.println("STAX parser");
        StaxParser staxParser = new StaxParser();
        beers = staxParser.parse(fileName);
        for (Beer item : beers) {
            System.out.println(item.toString());
        }
        return beers;
    }

    public static void main(String[] args) throws IOException, TransformerException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "data/beer.xml";
        String schemaName = "data/Beers.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        List<Beer> beers = new ArrayList<>();
        System.out.println("Press 1 to validate XML with XSD");
        System.out.println("Press 2 to use SAX parser for reading info from XML to java objects ");
        System.out.println("Press 3 to use STAX parser for reading info from XML to java objects ");
        System.out.println("Press 4 to use DOM parser for reading info from XML to java objects ");
        System.out.println("Press 5 to sort objects by name, manufacture,alcohol existing ,volume fraction of alcohol and nutritional value using comparator");
        System.out.println("Press 6 to transform XML-file into new XML-file with changed root element using XSL");
        System.out.println("Press 7 to transform XML-file into HTML-file sorting beers by beer's name");
        System.out.println("Press 8 to save beer objects to database");
        Scanner scan = new Scanner(System.in);
        int task = scan.nextInt();
        if (task == 1) {

            try {
                Schema schema = factory.newSchema(schemaLocation);
                Validator validator = schema.newValidator();
                Source source = new StreamSource(fileName);
                validator.validate(source);
                System.out.println("XML is valid.");
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
        } else if (task == 2) {
            beers = saxReadFromXML(fileName);

        } else if (task == 3) {
            beers = staxReadFromXML(fileName);

        } else if (task == 4) {
            beers = domReadFromXML(fileName);

        } else if (task == 5) {
            beers = domReadFromXML(fileName);
            System.out.println("________________________");
            System.out.println("Sort beers using comparator: ");
            System.out.println("Sort by name :\n");
            beers.sort(BeerComparator.beerNameComparator);

            for (Beer item : beers) {
                System.out.println(item.toString());
            }


            System.out.println("Sort by manufacture:\n");
            beers.sort(BeerComparator.manufactureComparator);

            for (Beer item : beers) {
                System.out.println(item.toString());
            }


            System.out.println("Sort by alcohol existing :\n");
            beers.sort(BeerComparator.alcoholExistComparator);

            for (Beer item : beers) {
                System.out.println(item.toString());
            }
            System.out.println("Sort by volume fraction of alcohol :\n");
            beers.sort(BeerComparator.volumeAlcoholFractionComparator);

            for (Beer item : beers) {
                System.out.println(item.toString());
            }
            System.out.println("Sort by nutritional value :\n");
            beers.sort(BeerComparator.nutritionalValueComparator);

            for (Beer item : beers) {
                System.out.println(item.toString());
            }

        } else if (task == 6) {

            System.out.println("________________________");
            System.out.println("Transform XML-file into new XML-file with changed root element using XSL:");
            try {
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer(new StreamSource("data/BeersChange4.xsl"));
                transformer.transform(new StreamSource("data/beer.xml"),
                        new StreamResult("data/newChangeBeers.xml"));
                System.out.println("Transform " + fileName + " complete");
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } else if (task == 7) {
            System.out.println("________________________");
            System.out.println("Transform XML-file into HTML-file sorting beers by beer's name using XSL:");
            try {
                TransformerFactory tf = TransformerFactory.newInstance();

                Transformer transformer = tf.newTransformer(new StreamSource("data/Beertest.xsl"));

                System.out.println("start transform");
                transformer.transform(new StreamSource("data/beer.xml"),
                        new StreamResult("data/newbeer.html"));
                System.out.println("New HTML File was created");


            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } else if (task == 8) {
            beers = domReadFromXML(fileName);

            MySqlService mySqlService = new MySqlService();
            Connection connection = mySqlService.getConnection();

            BeerService beerService = new BeerService();

            System.out.println("________________________");
            System.out.println("Saving to MySQL Database.");
            System.out.println("________________________");
            beerService.save(connection, beers);
            System.out.println("Printing all saved data from MySQL DB:");
            System.out.println("________________________");
            beerService.print(connection);
            System.out.println("________________________");

            mySqlService.disconnect();
        }
    }
}

