package com.datacorp.sistemainventario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Pattern;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long prodId;
    private String prodName;
    private String prodDescription;
    private String prodUnitPrice;
    private String prodCurrentStock;
    private Double purchasePrice;
    private Double sellingPrice;
    private String prodMinStock;
    @JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private String createBy;
    private String updatedBy;
    private Long cateId;

}
