package com.company.autoservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "media")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String fileDownloadUri;
}
