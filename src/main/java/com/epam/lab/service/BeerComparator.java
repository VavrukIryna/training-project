package com.epam.lab.service;

import com.epam.lab.model.Beer;

import java.util.Comparator;

public class BeerComparator implements Comparator<Beer> {
    //Comparator by name
    public static Comparator<Beer> beerNameComparator = new Comparator<Beer>() {

        @Override
        public int compare(Beer beer1, Beer beer2) {
            return beer1.getName().compareTo(beer2.getName());
        }
    };

    //Comparator by manufacturer
    public static Comparator<Beer> manufactureComparator = new Comparator<Beer>() {

        @Override
        public int compare(Beer beer1, Beer beer2) {
            return beer1.getManufacturer().compareTo(beer2.getManufacturer());
        }
    };

    //Comparator by alcohol exist
    public static Comparator<Beer> alcoholExistComparator = new Comparator<Beer>() {

        @Override
        public int compare(Beer beer1, Beer beer2) {
            return beer1.getAi().compareTo(beer2.getAi());
        }
    };
    //Comparator volume Alcohol Fraction
    public static Comparator<Beer> volumeAlcoholFractionComparator = new Comparator<Beer>() {

        @Override
        public int compare(Beer beer1, Beer beer2) {
            return beer1.getChars().getVolumeAlcoholFraction().compareTo(beer2.getChars().getVolumeAlcoholFraction());
        }
    };
    //Comparator nutritional Value (kkal)
    public static Comparator<Beer> nutritionalValueComparator = new Comparator<Beer>() {

        @Override
        public int compare(Beer beer1, Beer beer2) {
            return beer1.getChars().getNutritionalValue().compareTo(beer2.getChars().getNutritionalValue());
        }
    };

    @Override
    public int compare(Beer o1, Beer o2) {
        // use default one (by name):
        return beerNameComparator.compare(o1, o2);
    }
}
