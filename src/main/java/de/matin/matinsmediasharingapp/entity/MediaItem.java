package de.matin.matinsmediasharingapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name= "media_items")
public class MediaItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="id", updatable = false, nullable = false)
    private  UUID uuid;
    private String uploadedBy;

    @Column(name = "name", nullable = false)
    private String mediaName;
    private long fileSize;
    private String filePath;

    @Column(name = "upload_date", nullable = false, updatable = false)
    private LocalDateTime uploadDate = LocalDateTime.now();

    @Column(name ="file_type", nullable = false)
    private String fileType;




}
