package com.fiap.br.services;

import java.util.List;

import com.fiap.br.models.Contrato;
import com.fiap.br.repositories.ContratoRepository;
import com.fiap.br.util.validator.FieldValidator;

public class ContratoService {
    private ContratoRepository ContratoRepository;

    public ContratoService(ContratoRepository contratoRepository) {
        this.ContratoRepository = contratoRepository;
    }

    public Contrato getContratoById(int id) {
        return ContratoRepository.findOne(Contrato.class, id);
    }

    public List<Contrato> getContratos() {
        return ContratoRepository.findAll(Contrato.class);
    }

    public boolean createContrato(Contrato contrato) {
        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(contrato);

            if (!isValid) {
                return false;
            }

            ContratoRepository.save(contrato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateContrato(Contrato contrato, int id) {
        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(contrato);

            if (!isValid) {
                return false;
            }

            ContratoRepository.update(contrato, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteContrato(int id) {
        ContratoRepository.delete(Contrato.class, id);
    }
}
