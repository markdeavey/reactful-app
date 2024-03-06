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

import com.mjdb.reactful._dto.CategoryDTO;
import com.mjdb.reactful._service.ICategoryService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final ICategoryService service;

  @GetMapping
  public Flux<CategoryDTO> list(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
  ) {
    return service.list(PageRequest.of(page, size));
  }

  @PostMapping("/register")
  Mono<CategoryDTO> register(@RequestBody CategoryDTO category) throws Exception {
    return service.register(category);
  }

  @PutMapping("/update" + "/{categoryId}")
  Mono<CategoryDTO> update(
    @PathVariable Integer categoryId,
    @RequestBody CategoryDTO category
  ) throws Exception {
    return service.update(categoryId, category);
  }

  @GetMapping("/{id}")
  Mono<CategoryDTO> findById(@PathVariable Integer id) throws Exception {
    return service.findById(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable Integer id) {
    return service.delete(id);
  }

  @GetMapping("/hateoas/{id}")
  Mono<CategoryDTO> hateoas(@PathVariable Integer id) throws Exception {
    return service.findById(id);
  }
}
