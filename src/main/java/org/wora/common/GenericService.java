package org.wora.common;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {
    T save(T entity);
    List<T> findAll();
    T update(T entity);
    Optional<T> findById(ID id);
}
