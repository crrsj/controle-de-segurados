package br.com.seguros.repositorio;

import br.com.seguros.entidades.Segurado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguradoRepositorio extends JpaRepository<Segurado,Long> {
}
