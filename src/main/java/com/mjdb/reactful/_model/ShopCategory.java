package com.mjdb.reactful._model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("shop_category")
public class ShopCategory {

    @Id
    @Column("id")
    private Integer id;

    @Column("shop_id")
    private Integer shopId;

    @Column("category_id")
    private Integer categoryId;
}
