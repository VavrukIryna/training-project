package com.epam.lab.DAO;

import com.epam.lab.model.Chars;
import com.epam.lab.model.SpillMethod;
import com.epam.lab.transformer.CharsTransformer;
import com.sun.xml.internal.bind.v2.TODO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharsDao implements CRUD<Chars> {
    private CharsTransformer charsTransformer;
    private SpillMethodDao spillMethodDao;

    public CharsDao() {
        this.charsTransformer = new CharsTransformer();
        this.spillMethodDao = new SpillMethodDao();
    }

    @Override
    public Chars readOne(Connection connection, Integer id) {
        Chars chars = null;

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM chars JOIN spill_method ON chars.id_chars=spill_method.id_spill_method WHERE id_chars='" + String.valueOf(id) + "';";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {
                chars = charsTransformer.transform(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chars;
    }

    @Override
    public List<Chars> readAll(Connection connection) {
        List<Chars> charsList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String sqlQuery = "SELECT * FROM chars JOIN spill_method ON chars.id_spill_method=spill_method.id_spill_method";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                charsList.add(charsTransformer.transform(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return charsList;
    }

    @Override
    public Chars create(Connection connection, Chars chars) {

        // Let's work with FK.
        SpillMethod spillMethod = spillMethodDao.create(connection, chars.getSpillMethod());
        chars.getSpillMethod().setIdSpillMethod(spillMethod.getIdSpillMethod());

        String sqlQuery = "INSERT INTO chars (id_chars,volume_alcohol_fraction,clearness, filtered, nutritional_value,id_spill_method) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            PreparedStatement preparedStatementPopulated = charsTransformer.transform(preparedStatement, chars);
            int rowsUpdated = preparedStatementPopulated.executeUpdate();

            if (rowsUpdated == 0) {
                throw new SQLException("Creating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatementPopulated.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chars.setIdChars(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating failed, no ID obtained.");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chars;
    }


    public Chars update(Connection connection, Chars chars) {
        SpillMethod spillMethod = spillMethodDao.create(connection, chars.getSpillMethod());
        chars.getSpillMethod().setIdSpillMethod(spillMethod.getIdSpillMethod());

        String sqlQuery = "UPDATE chars SET id_chars = ?, volume_alcohol_fraction = ?, clearness= ?, filtered= ?, nutritional_value=?, id_spill_method=? WHERE id_chars = '"
                + String.valueOf(chars.getIdChars()) + "'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            PreparedStatement preparedStatementPopulated = charsTransformer.transform(preparedStatement, chars);
            preparedStatementPopulated.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chars;
    }


    public void delete(Connection connection, Integer id) {
        String deleteSQL = "DELETE FROM chars WHERE id_chars= ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            PreparedStatement preparedStatementPopulated = charsTransformer.transformID(preparedStatement, id);
            preparedStatementPopulated.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
