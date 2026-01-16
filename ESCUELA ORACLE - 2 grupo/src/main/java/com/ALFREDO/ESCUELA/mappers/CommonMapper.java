package com.ALFREDO.ESCUELA.mappers;

public interface CommonMapper<RQ, RS, E> {

    RS entityToResponse(E entity);

    E requestToEntity(RQ request);


}
