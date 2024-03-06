package com.mjdb.reactful._service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mjdb.reactful._dto.ShopDTO;
import com.mjdb.reactful._model.Shop;
import com.mjdb.reactful._repo.IShopRepo;
import com.mjdb.reactful._service.IShopService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements IShopService {

  private final IShopRepo shopRepo;

  @Override
  public Mono<ShopDTO> register(ShopDTO shopDTO) {
    return shopRepo.save(ConvertToEntity(shopDTO)).map(this::ConvertToDTO);
  }

  @Override
  public Mono<ShopDTO> update(Integer id, ShopDTO shopDTO) {
    return shopRepo
      .findById(id)
      .flatMap(shop -> {
        shop.setAddress(shopDTO.getAddress());
        shop.setCity(shopDTO.getCity());
        shop.setCountry(shopDTO.getCountry());
        shop.setEmail(shopDTO.getEmail());
        shop.setShop_name(shopDTO.getShop_name());
        shop.setPhone(shopDTO.getPhone());
        shop.setWebsite(shopDTO.getWebsite());
        shop.setZipCode(shopDTO.getZipCode());
        shop.setDescription(shopDTO.getDescription());
        shop.setId(id);
        return shopRepo.save(shop);
      })
      .map(this::ConvertToDTO);
  }

  @Override
  public Flux<ShopDTO> list(Pageable pageable) {
    return shopRepo
      .findAll(Sort.by(Sort.Direction.ASC, "id")) // Ordenar por ID u otro campo
      .skip(pageable.getPageNumber() * pageable.getPageSize()) // Saltar elementos según la página solicitada
      .take(pageable.getPageSize()) // Tomar solo el tamaño de la página solicitado
      .map(this::ConvertToDTO);
  }

  @Override
  public Mono<ShopDTO> findById(Integer id) {
    return shopRepo.findById(id).map(this::ConvertToDTO);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return shopRepo.deleteById(id);
  }

  @Override
  public Mono<Boolean> existsById(Integer id) {
    return shopRepo.existsById(id);
  }

  @Override
  public Mono<Long> count() {
    return shopRepo.count();
  }

  @Override
  public Mono<Void> deleteAll() {
    return shopRepo.deleteAll();
  }

  public ShopDTO ConvertToDTO(Shop shop) {
    if (shop == null) {
      return ShopDTO.builder().build();
    }

    return ShopDTO
      .builder()
      .id(shop.getId())
      .address(shop.getAddress())
      .city(shop.getCity())
      .country(shop.getCountry())
      .email(shop.getEmail())
      .shop_name(shop.getShop_name())
      .phone(shop.getPhone())
      .website(shop.getWebsite())
      .zipCode(shop.getZipCode())
      .description(shop.getDescription())
      .build();
  }

  public Shop ConvertToEntity(ShopDTO shopDTO) {
    return Shop
      .builder()
      .address(shopDTO.getAddress())
      .city(shopDTO.getCity())
      .country(shopDTO.getCountry())
      .email(shopDTO.getEmail())
      .shop_name(shopDTO.getShop_name())
      .phone(shopDTO.getPhone())
      .website(shopDTO.getWebsite())
      .zipCode(shopDTO.getZipCode())
      .description(shopDTO.getDescription())
      .build();
  }
}
