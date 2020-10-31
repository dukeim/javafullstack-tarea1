package com.jego.tareabackend.service.impl;

import com.jego.tareabackend.model.Venta;
import com.jego.tareabackend.repo.IGenericRepo;
import com.jego.tareabackend.repo.IVentaRepo;
import com.jego.tareabackend.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {
    @Autowired
    private IVentaRepo repo;

    @Override
    protected IGenericRepo<Venta, Integer> getRepo() {
        return repo;
    }

    @Transactional
    @Override
    public Venta registrarTransaccional(Venta venta) throws Exception {
        venta.getDetalleVenta().forEach(det -> det.setVenta(venta));

        repo.save(venta);

        return venta;
    }
}
