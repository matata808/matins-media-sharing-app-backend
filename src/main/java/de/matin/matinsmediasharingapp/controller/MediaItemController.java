package de.matin.matinsmediasharingapp.controller;


import de.matin.matinsmediasharingapp.domain.dto.MediaItemDTO;
import de.matin.matinsmediasharingapp.domain.dto.MediaItemUploadRequest;
import de.matin.matinsmediasharingapp.domain.services.MediaItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

//TODO: test backend with postman
@RestController
@RequestMapping(path ="/api/v1/media")
public class MediaItemController {
    final MediaItemService service;

    public MediaItemController(MediaItemService mediaItemService){
        this.service = mediaItemService;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MediaItemDTO upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy
    ) throws IOException {
        MediaItemUploadRequest request = new MediaItemUploadRequest();
        request.setFile(file);
        request.setUploadedBy(uploadedBy);

        return service.uploadFile(request);
    }

    @GetMapping
    public List<MediaItemDTO> list(){
        return service.getAllMediaItems();
    }
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID uuid){
        service.deleteMedia(uuid);
    }

}
