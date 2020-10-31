package com.jego.tareabackend.service.impl;

import com.jego.tareabackend.model.Producto;
import com.jego.tareabackend.repo.IGenericRepo;
import com.jego.tareabackend.repo.IPersonaRepo;
import com.jego.tareabackend.repo.IProductoRepo;
import com.jego.tareabackend.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends CRUDImpl<Producto, Integer> implements IProductoService {
    @Autowired
    private IProductoRepo repo;

    @Override
    protected IGenericRepo<Producto, Integer> getRepo() {
        return repo;
    }
}
