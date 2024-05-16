package com.fiap.br.services;

import com.fiap.br.models.Plano;
import com.fiap.br.repositories.PlanoRepository;

import java.util.List;

public class PlanoService {
    private PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public Plano findPlanoById(int id) {
        return planoRepository.findOne(Plano.class, id);
    }

    public List<Plano> findAllPlanos() {
        return planoRepository.findAll(Plano.class);
    }

    public void savePlano(Plano plano) {
        planoRepository.save(plano);
    }

    public void updatePlano(Plano plano, int id) {
        planoRepository.update(plano, id);
    }

    public void deletePlano(int id) {
        planoRepository.delete(Plano.class, id);
    }
}
