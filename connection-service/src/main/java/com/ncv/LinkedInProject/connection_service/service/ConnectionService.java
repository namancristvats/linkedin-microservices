package com.ncv.LinkedInProject.connection_service.service;

import com.ncv.LinkedInProject.connection_service.entity.Person;
import com.ncv.LinkedInProject.connection_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionService {
    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnection(Long userId){
        log.info("Getting First Degree Connection Of User With Id: "+userId);
        List<Person> lists=personRepository.getFirstDegreeConnections(userId);
        return lists;
    }
}
