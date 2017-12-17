package com.epam.lab.service;

import com.epam.lab.DAO.BeerDao;
import com.epam.lab.model.*;

import java.sql.Connection;
import java.util.List;


public class BeerService {

    /**
     * Saves all beers objects to database
     *
     * @param connection - database connection.
     * @param beers      - collection of beers.
     */
    public void save(Connection connection, List<Beer> beers) {
        BeerDao beerDao = new BeerDao();
        beers.forEach(beer -> beerDao.create(connection, beer));
    }

    /**
     * Prints with default System.out all beers objects.
     *
     * @param connection - database connection.
     */
    public void print(Connection connection) {
        List<Beer> beers = new BeerDao().readAll(connection);
        beers.forEach(System.out::println);
    }

}