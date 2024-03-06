package com.mjdb.reactful._model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "personas")
public class Persona {

  @Id
  private Integer id;

  private String firstName;

  private String lastName;

  private String phone;

  private String email;
}
