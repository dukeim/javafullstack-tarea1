package com.jego.tareabackend.controller;

import com.jego.tareabackend.exception.ModeloNotFoundException;
import com.jego.tareabackend.model.Producto;
import com.jego.tareabackend.service.IProductoService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() throws Exception {
        List<Producto> lista = service.listar();
        return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id) throws Exception {
        Producto obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }
        return new ResponseEntity<Producto>(obj, HttpStatus.OK);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<Producto> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
        Producto obj = service.listarPorId(id);

        if (obj.getIdProducto() == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        // localhost:8080/Productos/{id}
        EntityModel<Producto> recurso = EntityModel.of(obj);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));

        recurso.add(linkTo.withRel("Producto-recurso"));

        return recurso;
    }

    /*
     * @PostMapping public ResponseEntity<Producto> registrar(@Valid @RequestBody
     * Producto p) { Producto obj = service.registrar(p); return new
     * ResponseEntity<Producto>(obj, HttpStatus.CREATED); }
     */

    @PostMapping
    public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto p) throws Exception {
        Producto obj = service.registrar(p);

        // localhost:8080/Productos/2
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdProducto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto p) throws Exception {
        Producto obj = service.modificar(p);
        return new ResponseEntity<Producto>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Producto obj = service.listarPorId(id);

        if (obj == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
