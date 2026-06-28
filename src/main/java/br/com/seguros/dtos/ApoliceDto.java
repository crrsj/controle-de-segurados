package br.com.seguros.dtos;

import br.com.seguros.entidades.Segurado;
import br.com.seguros.enums.Cia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class ApoliceDto {

    private Long id;
    private LocalDate dataCadastro = LocalDate.now();
    private String nomeContato;
    private String telefone;
    private String email;
    private LocalDate vigenciaInicial;
    private LocalDate vigenciaFinal;
    private Cia cia;
    private Double porcentagemComissao;
    private int parcelas;
    private BigDecimal premioLiquido;
    private BigDecimal premioBruto;
    private BigDecimal totalComissao;
    private boolean ativa = true;
    private Segurado segurado;

}
