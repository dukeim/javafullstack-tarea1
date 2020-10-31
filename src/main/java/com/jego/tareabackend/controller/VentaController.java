package com.jego.tareabackend.controller;

import com.jego.tareabackend.model.Venta;
import com.jego.tareabackend.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private IVentaService service;

    @PostMapping
    public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta venta) throws Exception {
        Venta obj = service.registrarTransaccional(venta);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getIdVenta()).toUri();
        return ResponseEntity.created(location).build();
    }
}
