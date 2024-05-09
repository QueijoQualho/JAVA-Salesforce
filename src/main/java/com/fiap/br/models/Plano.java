package com.fiap.br.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.fiap.br.models.enums.TipoPlano;

import lombok.Data;

@Data
public class Plano {
    private Long id;
    private String nomeFantasia;
    private TipoPlano tipoPlano;
    private LocalDate dataInicio;
    private LocalDate dataFinal;
    private double valor;
    private List<Produto> listaProdutos = new ArrayList<Produto>();

    public Plano(String nomeFantasia, TipoPlano tipoPlano, Long id) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.dataInicio = LocalDate.now();
        this.tipoPlano = tipoPlano;

        switch (tipoPlano) {
            case ANUAL:
                this.dataFinal = dataInicio.plusYears(1);
                break;
            case SEMESTRAL:
                this.dataFinal = dataInicio.plusMonths(6);
                break;
            case TRIMESTRAL:
                this.dataFinal = dataInicio.plusMonths(3);
                break;
            case MENSAL:
                this.dataFinal = dataInicio.plusMonths(1);
                break;
            default:
                throw new IllegalArgumentException("Tipo de plano inválido");
        }
        calcDataFinal();
    }

    /* Method */
    public void addProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    private void calcDataFinal(){
        switch (this.tipoPlano) {
            case ANUAL:
                this.dataFinal = dataInicio.plusYears(1);
                break;
            case SEMESTRAL:
                this.dataFinal = dataInicio.plusMonths(6);
                break;
            case TRIMESTRAL:
                this.dataFinal = dataInicio.plusMonths(3);
                break;
            case MENSAL:
                this.dataFinal = dataInicio.plusMonths(1);
                break;
            default:
                throw new IllegalArgumentException("Tipo de plano inválido");
        }
    }

    public void calcValor() {
        double valorTotal = 0;

        if (dataInicio.isBefore(dataFinal)) {
            long meses = ChronoUnit.MONTHS.between(dataInicio, dataFinal);
            for (Produto produto : listaProdutos) {
                valorTotal += produto.getPreco() * meses;
            }
        }

        this.valor = valorTotal;
    }
}
