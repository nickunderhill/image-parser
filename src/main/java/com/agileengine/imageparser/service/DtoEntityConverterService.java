package com.agileengine.imageparser.service;

/**
 * Converts entity object E to dto object D and vise versa
 *
 * @param <E> - entity object
 * @param <D> - dto object
 */
public interface DtoEntityConverterService<E, D> {

    public D entityToDto(E e);

    public E dtoToEntity(D d);

}
