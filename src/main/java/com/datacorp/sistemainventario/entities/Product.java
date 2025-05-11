package com.datacorp.sistemainventario.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Product {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long prodId;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "prod_description")
    private String prodDescription;
    @Column(name = "prod_unit_price")
    private String prodUnitPrice;
    @Column(name = "prod_current_stock")
    private String prodCurrentStock;
    @Column(name = "purchase_price")
    private Double purchasePrice;
    @Column(name = "selling_price")
    private Double sellingPrice;
    @Column(name = "prod_min_stock")
    private String prodMinStock;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "cate_id")
    private Long cateId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cate_id", insertable = false, updatable = false)
    private  Category category;
}
