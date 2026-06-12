package com.nvc.LinkedInProject.uploaderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class UploaderController {
    private final UploaderService uploaderService;
    @GetMapping
    public String insideUploader(){
        return "Inside Controller";
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart("file")MultipartFile file){
        String url= uploaderService.uploadFile(file);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }
}
