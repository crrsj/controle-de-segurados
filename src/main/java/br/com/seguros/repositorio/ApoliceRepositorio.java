package br.com.seguros.repositorio;

import br.com.seguros.entidades.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ApoliceRepositorio extends JpaRepository<Apolice,Long> {
    List<Apolice> findAllByAtivaTrue();
    List<Apolice> findAllByAtivaFalse();

    @Query("SELECT " +
            "COALESCE(SUM(a.premioLiquido * a.porcentagemComissao / 100), 0) as totalComissao, " +
            "COALESCE(SUM(a.premioLiquido), 0) as totalPremioLiquido, " +
            "COALESCE(SUM(a.premioBruto), 0) as totalPremioBruto " +
            "FROM Apolice a " +
            "WHERE a.ativa = true " +
            "AND a.vigenciaFinal BETWEEN :dataInicio AND :dataFim")
    RelatoriosTotaisProjecao calcularResumoFinanceiroPorPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );
}
