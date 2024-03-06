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

import com.mjdb.reactful._dto.PersonaDTO;
import com.mjdb.reactful._service.IPersonaService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/personas")
public class PersonaController {

  private final IPersonaService service;

  @GetMapping
  public Flux<PersonaDTO> list(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
  ) {
    return service.list(PageRequest.of(page, size));
  }

  @PostMapping("/register")
  Mono<PersonaDTO> register(@RequestBody PersonaDTO persona) throws Exception {
    return service.register(persona);
  }

  @PutMapping("/update" + "/{personaId}")
  Mono<PersonaDTO> update(
    @PathVariable Integer personaId,
    @RequestBody PersonaDTO persona
  ) throws Exception {
    return service.update(personaId, persona);
  }

  @GetMapping("/{id}")
  Mono<PersonaDTO> findById(@PathVariable Integer id) throws Exception {
    return service.findById(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> delete(@PathVariable Integer id) {
    return service.delete(id);
  }

  @GetMapping("/hateoas/{id}")
  Mono<PersonaDTO> hateoas(@PathVariable Integer id) throws Exception {
    return service.findById(id);
  }
}
