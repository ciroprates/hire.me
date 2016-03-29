package com.bemobi.shortener;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortRepository extends MongoRepository<Short, String> {


}