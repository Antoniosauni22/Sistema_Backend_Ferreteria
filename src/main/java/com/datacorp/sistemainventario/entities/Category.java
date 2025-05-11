package com.datacorp.sistemainventario.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Category {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cateId;
   //  @Column(name = "cate_description")
    private String  cateDescription;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private  String  createdBy;
    private String updatedBy;
}
