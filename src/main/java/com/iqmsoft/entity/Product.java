package com.iqmsoft.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Product {
    
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private boolean messageReceived;
    private Integer messageCount = 0;
    
}
