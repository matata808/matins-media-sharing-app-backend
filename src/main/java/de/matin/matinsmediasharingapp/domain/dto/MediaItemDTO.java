package de.matin.matinsmediasharingapp.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MediaItemDTO {

    private UUID uuid;
    private String mediaName;
    private String fileType;
    private long fileSize;
    private String uploadedBy;
    private LocalDateTime uploadDate;


}
