package de.matin.matinsmediasharingapp.domain.dto;

import de.matin.matinsmediasharingapp.domain.entity.MediaItem;
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


    // Entity → DTO (when sending data OUT to React)
    private MediaItemDTO toDTO(MediaItem item) {
        MediaItemDTO dto = new MediaItemDTO();
        dto.setUuid(item.getUuid());
        dto.setMediaName(item.getMediaName());
        dto.setFileType(item.getFileType());
        dto.setFileSize(item.getFileSize());
        dto.setUploadedBy(item.getUploadedBy());
        dto.setUploadDate(item.getUploadDate());
        return dto;
    }

    // DTO → Entity (when receiving data IN from React)
    private MediaItem toEntity(MediaItemDTO dto) {
        MediaItem item = new MediaItem();
        item.setMediaName(dto.getMediaName());
        item.setFileType(dto.getFileType());
        item.setFileSize(dto.getFileSize());
        item.setUploadedBy(dto.getUploadedBy());
        return item;
    }

}
