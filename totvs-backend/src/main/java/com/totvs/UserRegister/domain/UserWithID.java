package com.totvs.UserRegister.domain;

/**
 * The type User with id. Used for the object returned from the Json API server.
 */
public record UserWithID(String id,
                         String name,
                         String address,
                         String neighborhood) {

}
