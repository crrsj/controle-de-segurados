package br.com.seguros.dto;

import br.com.seguros.entidades.Apolice;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeguradoDto {

    private Long id;
    private String nome;
    private String cpf;
    private List<Apolice> apolices = new ArrayList<>();

}
