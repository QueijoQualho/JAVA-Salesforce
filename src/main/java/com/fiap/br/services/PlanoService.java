package com.fiap.br.services;

import com.fiap.br.models.Plano;
import com.fiap.br.repositories.PlanoRepository;
import com.fiap.br.util.validator.FieldValidator;

import java.util.List;

public class PlanoService {
    private PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public Plano getPlanoById(int id) {
        return planoRepository.findOne(Plano.class, id);
    }

    public List<Plano> getPlanos() {
        return planoRepository.findAll(Plano.class);
    }

    public boolean createPlano(Plano plano) {
        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(plano);

            if (!isValid) {
                return false;
            }

            planoRepository.save(plano);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updatePlano(Plano plano, int id) {

        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(plano);

            if (!isValid) {
                return false;
            }

            planoRepository.update(plano, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deletePlano(int id) {
        planoRepository.delete(Plano.class, id);
    }
}
