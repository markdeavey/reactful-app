package com.mjdb.reactful._service;

import org.springframework.data.domain.Pageable;

import com.mjdb.reactful.exceptions.DataNotFoundException;
import com.mjdb.reactful.exceptions.DataPersistenceException;
import com.mjdb.reactful.exceptions.DataValidationException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICRUD<T, ID> {
  Mono<T> register(T t)
    throws DataValidationException, DataPersistenceException;
  Mono<T> update(ID id, T t)
    throws DataNotFoundException, DataValidationException, DataPersistenceException;
  Flux<T> list(Pageable pageable) throws DataPersistenceException;

  Mono<T> findById(ID id)
    throws DataNotFoundException, DataPersistenceException;
  Mono<Void> delete(ID id)
    throws DataNotFoundException, DataPersistenceException;

  Mono<Boolean> existsById(ID id) throws DataPersistenceException;
  Mono<Long> count() throws DataPersistenceException;
  Mono<Void> deleteAll() throws DataPersistenceException;
}
