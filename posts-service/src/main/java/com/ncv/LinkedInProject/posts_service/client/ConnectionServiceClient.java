package com.ncv.LinkedInProject.posts_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.ncv.LinkedInProject.posts_service.Dtos.PersonDto;

import java.util.List;

@FeignClient(name="connections-service",path="/connections",url = "${CONNECTIONS_SERVICE_URI:}")
public interface ConnectionServiceClient {
    @GetMapping("/core/{userId}/firstDegree")
    List<PersonDto> getFirstDegreeConnection(@PathVariable Long userId);
}
