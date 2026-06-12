package com.ncv.LinkedInProject.connection_service.controller;

import com.ncv.LinkedInProject.connection_service.entity.Person;
import com.ncv.LinkedInProject.connection_service.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
@Slf4j
public class ConnectionsController {
    private final ConnectionService connectionService;

    @GetMapping("/{userId}/firstDegree")
    public List<Person> getFirstDegreeConnection(@PathVariable Long userId){
        return connectionService.getFirstDegreeConnection(userId);
    }
}
