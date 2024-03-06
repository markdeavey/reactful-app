package com.mjdb.reactful._controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mjdb.reactful._dto.ShopDTO;
import com.mjdb.reactful._service.IShopService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shops")
public class ShopController {

  private final IShopService service;

  @GetMapping
  public Flux<ShopDTO> list(
    @RequestParam(value= "page", defaultValue = "1") Integer page,
    @RequestParam(value= "size", defaultValue = "10") Integer size
  ) {
    return service.list(PageRequest.of(page, size));
  }

  @PostMapping("/register")
  Mono<ShopDTO> register(@RequestBody ShopDTO shop) throws Exception {
    return service.register(shop);
  }

  @PutMapping("/update" + "/{shopId}")
  Mono<ShopDTO> update(@PathVariable Integer shopId, @RequestBody ShopDTO shop)
    throws Exception {
    return service.update(shopId, shop);
  }

  @GetMapping("/{id}")
  Mono<ShopDTO> findById(@PathVariable Integer id) throws Exception {
    return service.findById(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable Integer id) {
    return service.delete(id);
  }

  @GetMapping("/hateoas/{id}")
  Mono<ShopDTO> hateoas(@PathVariable Integer id) throws Exception {
    return service.findById(id);
  }
}
