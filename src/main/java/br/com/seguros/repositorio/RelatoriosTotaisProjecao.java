package br.com.seguros.repositorio;

import java.math.BigDecimal;

public interface RelatoriosTotaisProjecao {
    BigDecimal getTotalComissao();
    BigDecimal getTotalPremioLiquido();
    BigDecimal getTotalPremioBruto();
}

