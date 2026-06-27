package br.com.seguros.entidades;


import br.com.seguros.enums.Cia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_apolices")
@Data
public class Apolice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeContato;
    private String telefone;
    @Email
    private String email;
    private LocalDate vigenciaInicial;
    private LocalDate vigenciaFinal;
    @Enumerated(EnumType.STRING)
    private Cia cia;
    private int comissao;
    private int parcelas;
    private BigDecimal Premio;
    private BigDecimal valorComissao;
    @ManyToOne
    @JoinColumn(name = "segurado_id")
    @JsonIgnore
    private Segurado segurado;
}
