package com.jego.tareabackend.service;

import com.jego.tareabackend.model.Venta;

public interface IVentaService extends ICRUD<Venta, Integer>{
    Venta registrarTransaccional(Venta venta) throws Exception;
}
