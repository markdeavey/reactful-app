package com.mjdb.reactful._model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categories")
public class Category {

  @Id
  private Integer id;

  private String category_name;

  private String description;
}
