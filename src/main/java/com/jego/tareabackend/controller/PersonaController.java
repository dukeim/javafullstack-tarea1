package com.jego.tareabackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.jego.tareabackend.exception.ModeloNotFoundException;
import com.jego.tareabackend.model.Persona;
import com.jego.tareabackend.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private IPersonaService service;

    @GetMapping
    public ResponseEntity<List<Persona>> listar() throws Exception {
        List<Persona> lista = service.listar();
        return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Persona obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }
        return new ResponseEntity<Persona>(obj, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<Persona> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Persona obj = service.listarPorId(id);

        if (obj.getIdPersona() == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        // localhost:8080/Personas/{id}
        EntityModel<Persona> recurso = EntityModel.of(obj);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));

        recurso.add(linkTo.withRel("Persona-recurso"));

        return recurso;
    }

    /*
     * @PostMapping public ResponseEntity<Persona> registrar(@Valid @RequestBody
     * Persona p) { Persona obj = service.registrar(p); return new
     * ResponseEntity<Persona>(obj, HttpStatus.CREATED); }
     */

    @PostMapping
    public ResponseEntity<Persona> registrar(@Valid @RequestBody Persona p) throws Exception {
        Persona obj = service.registrar(p);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdPersona()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Persona> modificar(@Valid @RequestBody Persona p) throws Exception {
        Persona obj = service.modificar(p);
        return new ResponseEntity<Persona>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Persona obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
