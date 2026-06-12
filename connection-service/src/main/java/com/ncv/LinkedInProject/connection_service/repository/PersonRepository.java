package com.ncv.LinkedInProject.connection_service.repository;

import com.ncv.LinkedInProject.connection_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person,Long> {
    @Query("match (personA:Person) -[:CONNECTED_TO]- (personB:Person) " +
    "where personA.userId= $userId "+
    "return personB")
    List<Person> getFirstDegreeConnections(@Param("userId") Long userId);
}
