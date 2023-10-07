package com.company.autoservice.entity.image;

import com.company.autoservice.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Document extends BaseEntity {

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String generatedName    ;

    @Column(nullable = false)
    private String path;

    private boolean deleted;
}