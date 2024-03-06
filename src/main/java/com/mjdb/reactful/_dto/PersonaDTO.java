package com.mjdb.reactful._dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonaDTO {

  private Integer id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String phone;

  private String email;
}
