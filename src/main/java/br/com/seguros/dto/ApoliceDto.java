package br.com.seguros.dto;

import br.com.seguros.entidades.Segurado;
import br.com.seguros.enums.Cia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ApoliceDto {

    private Long id;
    private String nomeContato;
    private String telefone;
    private String email;
    private LocalDate vigenciaInicial;
    private LocalDate vigenciaFinal;
    private Cia cia;
    private int comissao;
    private int parcelas;
    private BigDecimal Premio;
    private BigDecimal valorComissao;
    private Segurado segurado;
}
