package de.matin.matinsmediasharingapp.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MediaItemUploadRequest {
    private MultipartFile file;
    private  String uploadedBy;
}
