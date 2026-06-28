package br.com.seguros.dtos;

import java.math.BigDecimal;

public record RelatoriosTotaisDto(
        BigDecimal totalComissao,
        BigDecimal totalPremioLiquido,
        BigDecimal totalPremioBruto
) {
}
