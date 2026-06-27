package br.com.seguros.repositorio;

import br.com.seguros.entidades.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApoliceRepositorio extends JpaRepository<Apolice,Long> {
}
