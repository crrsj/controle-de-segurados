package br.com.seguros.repositorio;

import br.com.seguros.dtos.SeguradoDto;
import br.com.seguros.dtos.SeguradoSemCpf;
import br.com.seguros.entidades.Segurado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeguradoRepositorio extends JpaRepository<Segurado,Long> {
    Optional<Segurado> findByCpf(String cpf);
}
