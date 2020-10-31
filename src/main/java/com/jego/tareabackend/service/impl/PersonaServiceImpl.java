package com.jego.tareabackend.service.impl;

import com.jego.tareabackend.model.Persona;
import com.jego.tareabackend.repo.IGenericRepo;
import com.jego.tareabackend.repo.IPersonaRepo;
import com.jego.tareabackend.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl extends CRUDImpl<Persona, Integer> implements IPersonaService {

    @Autowired
    private IPersonaRepo repo;

    @Override
    protected IGenericRepo<Persona, Integer> getRepo() {
        return repo;
    }
}
