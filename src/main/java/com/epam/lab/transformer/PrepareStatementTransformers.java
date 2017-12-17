package com.epam.lab.transformer;

import java.sql.PreparedStatement;

public interface PrepareStatementTransformers<T> {
    PreparedStatement transform(PreparedStatement preparedStatement, T entity);
}
