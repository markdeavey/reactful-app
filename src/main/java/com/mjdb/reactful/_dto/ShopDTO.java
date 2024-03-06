package com.mjdb.reactful._dto;


import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;
import reactor.core.publisher.Flux;

@Builder
@Data
public class ShopDTO {

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

  private Flux<CategoryDTO> categories;
}
