package com.mjdb.reactful._service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mjdb.reactful._dto.CategoryDTO;
import com.mjdb.reactful._model.Category;
import com.mjdb.reactful._repo.ICategoryRepo;
import com.mjdb.reactful._service.ICategoryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

  private final ICategoryRepo categoryRepo;

  @Override
  public Mono<CategoryDTO> register(CategoryDTO categoryDTO) {
    return categoryRepo
      .save(ConvertToEntity(categoryDTO))
      .map(this::ConvertToDTO);
  }

  @Override
  public Mono<CategoryDTO> update(Integer id, CategoryDTO categoryDTO) {
    return categoryRepo
      .findById(id)
      .flatMap(category -> {
        category.setCategory_name(categoryDTO.getCategory_name());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepo.save(category);
      })
      .map(this::ConvertToDTO);
  }

  @Override
  public Flux<CategoryDTO> list(Pageable pageable) {
    return categoryRepo
      .findAll(Sort.by(Sort.Direction.ASC, "id")) // Ordenar por ID u otro campo
      .skip(pageable.getPageNumber() * pageable.getPageSize()) // Saltar elementos según la página solicitada
      .take(pageable.getPageSize()) // Tomar solo el tamaño de la página solicitado
      .map(this::ConvertToDTO);
  }

  @Override
  public Mono<CategoryDTO> findById(Integer id) {
    return categoryRepo.findById(id).map(this::ConvertToDTO);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return categoryRepo.deleteById(id);
  }

  @Override
  public Mono<Boolean> existsById(Integer id) {
    return categoryRepo.existsById(id);
  }

  @Override
  public Mono<Long> count() {
    return categoryRepo.count();
  }

  @Override
  public Mono<Void> deleteAll() {
    return categoryRepo.deleteAll();
  }

  public CategoryDTO ConvertToDTO(Category category) {
    if (category == null) {
      return CategoryDTO.builder().build();
    }

    return CategoryDTO
      .builder()
      .id(category.getId())
      .category_name(category.getCategory_name())
      .description(category.getDescription())
      .build();
  }

  public Category ConvertToEntity(CategoryDTO categoryDTO) {
    return Category
      .builder()
      .category_name(categoryDTO.getCategory_name())
      .description(categoryDTO.getDescription())
      .build();
  }
}
