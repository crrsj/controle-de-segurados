package br.com.seguros.repositorio;

import br.com.seguros.dtos.ApoliceDto;
import br.com.seguros.dtos.RelatoriosTotaisDto;
import br.com.seguros.entidades.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ApoliceRepositorio extends JpaRepository<Apolice,Long> {
    List<Apolice> findAllByAtivaTrue();
    List<Apolice> findAllByAtivaFalse();
    @Query("SELECT new br.com.seguros.dtos.RelatorioTotaisDto(" +
            "COALESCE(SUM(a.premioLiquido * (a.porcentagemComissao / 100)), 0), " + // 1. Soma total das comissões
            "COALESCE(SUM(a.premioLiquido), 0), " +                                // 2. Soma total do prêmio líquido
            "COALESCE(SUM(a.premioBruto), 0)) " +                                  // 3. Soma total do prêmio bruto
            "FROM Apolice a " +
            "WHERE a.ativa = true " +
            "AND a.vigenciaFinal BETWEEN :dataInicio AND :dataFim")
    RelatoriosTotaisDto calcularResumoFinanceiroPorPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );
}
