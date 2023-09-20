package com.gymbuddy.training.persistence.query;

import java.util.List;
import java.util.Optional;

/**
 * Interface class for basic Queries.
 */
public interface BaseQueryMapper<T> {
    List<T> getAllRecords();

    Optional<T> getRecordById(final Long id);
}
