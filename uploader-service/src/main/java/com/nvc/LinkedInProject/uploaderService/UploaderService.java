package com.nvc.LinkedInProject.uploaderService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UploaderService {

    String  uploadFile(MultipartFile file);
}
