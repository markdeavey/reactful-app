package com.mjdb.reactful._model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "shops")
public class Shop {

  @Id
  private Integer id;

  private String shop_name;

  private String description;

  private String phone;

  private String website;

  private String email;

  private String address;

  private String city;

  private String zipCode;

  private String country;
  
  private Flux<Category> categories;
}
