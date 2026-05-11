package de.matin.matinsmediasharingapp.domain.services;

import de.matin.matinsmediasharingapp.domain.dto.MediaItemDTO;
import de.matin.matinsmediasharingapp.domain.dto.MediaItemUploadRequest;
import de.matin.matinsmediasharingapp.domain.entity.MediaItem;
import de.matin.matinsmediasharingapp.repository.MediaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaItemService {
    private static final String UPLOAD_DIR = "uploads/";
    // 1. inject the repository here
    private final MediaItemRepository mediaItemRepository;

    // 2. a method to upload a file
    //    - takes MediaItemUploadRequest as parameter
    //    - saves file to disk
    //    - saves MediaItem to database
    //    - returns MediaItemDTO
    public MediaItemDTO uploadFile(MediaItemUploadRequest uploadRequest) throws IOException {
        MultipartFile file = uploadRequest.getFile();
        if(file.isEmpty()) throw new IllegalArgumentException("File cannot be empty");
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);

        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        MediaItem item = new MediaItem();
        item.setMediaName(file.getOriginalFilename());
        item.setFileType(file.getContentType());
        item.setFileSize(file.getSize());
        item.setFilePath(filePath.toString());

        MediaItem saved = mediaItemRepository.save(item);

        return toDTO(saved);


    }


    // 3. a method to get all media items
    //    - returns List<MediaItemDTO>
    public List<MediaItemDTO> getAllMediaItems() {
        return mediaItemRepository.findAll()
                .stream(). // gives List<MediaItem>
                map(this::toDTO). // converts each MediaItem -> MediaItemDTO
                collect(Collectors.toList()); // collects results back into a list
    }

    // 4. toDTO mapping method
    private MediaItemDTO toDTO(MediaItem item) {
        MediaItemDTO dto = new MediaItemDTO();
        dto.setUuid(item.getUuid());
        dto.setMediaName(item.getMediaName());
        dto.setFileSize(item.getFileSize());
        dto.setFileType(item.getFileType());
        dto.setUploadDate(item.getUploadDate());
        return dto;

    }

    // 5. toEntity mapping method
    private MediaItem toEntity(MediaItemDTO dto) {
        MediaItem item = new MediaItem();
        item.setMediaName(dto.getMediaName());
        item.setFileType(dto.getFileType());
        item.setFileSize(dto.getFileSize());
        item.setUploadedBy(dto.getUploadedBy());
        return item;
    }

    public void deleteMedia(UUID id){
        if(!mediaItemRepository.existsById(id)){
            throw new RuntimeException("Media not found");
        }
        mediaItemRepository.deleteById(id);
    }
}