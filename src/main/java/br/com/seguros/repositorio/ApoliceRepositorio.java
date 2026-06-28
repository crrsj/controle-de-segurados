package br.com.seguros.repositorio;

import br.com.seguros.dtos.ApoliceDto;
import br.com.seguros.entidades.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApoliceRepositorio extends JpaRepository<Apolice,Long> {
    List<Apolice> findAllByAtivaTrue();
    List<Apolice> findAllByAtivaFalse();
}
