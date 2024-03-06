package com.mjdb.reactful._dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDTO {

  private Integer id;

  @NotNull
  private String category_name;

  @NotNull
  private String description;
}
