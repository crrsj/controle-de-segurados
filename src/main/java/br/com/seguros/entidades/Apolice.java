package br.com.seguros.entidades;


import br.com.seguros.enums.Cia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "tb_apolices")
@Data
public class Apolice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_cadastro", updatable = false)
    private LocalDate dataCadastro = LocalDate.now();
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
    private BigDecimal premio;
    private BigDecimal valorComissao;
    private boolean ativa = true;
    @ManyToOne
    @JoinColumn(name = "segurado_id")
    @JsonIgnore
    private Segurado segurado;


    public int getAno() {
        return this.dataCadastro.getYear();
    }

    public int getMesNumero() {
        return this.dataCadastro.getMonthValue(); // Retorna de 1 a 12
    }

    /**
     * Calcula o valor do prêmio proporcional a uma semana (considerando o prêmio total)
     * Exemplo lógico simples: dividindo o prêmio total por 4 semanas.
     */
    public BigDecimal getPremioPorSemana() {
        if (this.premio == null) return BigDecimal.ZERO;
        return this.premio.divide(new BigDecimal("4"), 2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula o valor do prêmio proporcional por mês (baseado nas parcelas da apólice)
     */
    public BigDecimal getPremioPorMes() {
        if (this.premio == null || this.parcelas <= 0) return BigDecimal.ZERO;
        return this.premio.divide(new BigDecimal(this.parcelas), 2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula a comissão semanal baseada no valor total da comissão
     */
    public BigDecimal getValorComissaoPorSemana() {
        if (this.valorComissao == null) return BigDecimal.ZERO;
        return this.valorComissao.divide(new BigDecimal("4"), 2, RoundingMode.HALF_UP);
    }

    /**
     * Calcula a comissão mensal baseada nas parcelas
     */
    public BigDecimal getValorComissaoPorMes() {
        if (this.valorComissao == null || this.parcelas <= 0) return BigDecimal.ZERO;
        return this.valorComissao.divide(new BigDecimal(this.parcelas), 2, RoundingMode.HALF_UP);
    }
}
