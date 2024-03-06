package com.mjdb.reactful._service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mjdb.reactful._dto.PersonaDTO;
import com.mjdb.reactful._model.Persona;
import com.mjdb.reactful._repo.IPersonaRepo;
import com.mjdb.reactful._service.IPersonaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PersonaServiceImpl implements IPersonaService {

  private final IPersonaRepo personaRepo;

  @Override
  public Mono<PersonaDTO> register(PersonaDTO personaDTO) {
    return personaRepo
      .save(ConvertToEntity(personaDTO))
      .map(this::ConvertToDTO);
  }

  @Override
  public Mono<PersonaDTO> update(Integer id, PersonaDTO personaDTO) {
    return personaRepo
      .findById(id)
      .flatMap(persona -> {
        persona.setFirstName(personaDTO.getFirstName());
        persona.setLastName(personaDTO.getLastName());
        persona.setEmail(personaDTO.getEmail());
        persona.setPhone(personaDTO.getPhone());
        return personaRepo.save(persona);
      })
      .map(this::ConvertToDTO);
  }

  @Override
  public Flux<PersonaDTO> list(Pageable pageable) {
    return personaRepo
      .findAll(Sort.by(Sort.Direction.ASC, "id")) // Ordenar por ID u otro campo
      .skip(pageable.getPageNumber() * pageable.getPageSize()) // Saltar elementos según la página solicitada
      .take(pageable.getPageSize()) // Tomar solo el tamaño de la página solicitado
      .map(this::ConvertToDTO);
  }

  @Override
  public Mono<PersonaDTO> findById(Integer id) {
    return personaRepo.findById(id).map(this::ConvertToDTO);
  }

  @Override
  public Mono<Void> delete(Integer id) {
    return personaRepo.deleteById(id);
  }

  @Override
  public Mono<Boolean> existsById(Integer id) {
    return personaRepo.existsById(id);
  }

  @Override
  public Mono<Long> count() {
    return personaRepo.count();
  }

  @Override
  public Mono<Void> deleteAll() {
    return personaRepo.deleteAll();
  }

  public PersonaDTO ConvertToDTO(Persona persona) {
    if (persona == null) {
      return PersonaDTO.builder().build();
    }

    return PersonaDTO
      .builder()
      .id(persona.getId())
      .firstName(persona.getFirstName())
      .lastName(persona.getLastName())
      .email(persona.getEmail())
      .phone(persona.getPhone())
      .build();
  }

  public Persona ConvertToEntity(PersonaDTO personaDTO) {
    return Persona
      .builder()
      .firstName(personaDTO.getFirstName())
      .lastName(personaDTO.getLastName())
      .email(personaDTO.getEmail())
      .phone(personaDTO.getPhone())
      .build();
  }
}
