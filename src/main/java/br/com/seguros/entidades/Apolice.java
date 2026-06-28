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
    private BigDecimal porcentagemComissao;
    private int parcelas;
    private BigDecimal premioLiquido;
    private BigDecimal premioBruto;
    private BigDecimal totalComissao;
    private boolean ativa = true;
    @ManyToOne
    @JoinColumn(name = "segurado_id")
    @JsonIgnore
    private Segurado segurado;

    /**
     * Calcula a comissão individual cheia deste seguro.
     * Baseado estritamente no Prêmio Líquido.
     */
    public BigDecimal getValorComissao() {
        if (this.premioLiquido == null || this.porcentagemComissao == null) {
            return BigDecimal.ZERO;
        }
        // Conta: (Premio Liquido * Porcentagem) / 100
        return this.premioLiquido
                .multiply(this.porcentagemComissao)
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
    }
}
