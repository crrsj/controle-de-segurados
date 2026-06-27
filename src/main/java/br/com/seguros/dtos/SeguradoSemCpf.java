package br.com.seguros.dtos;

import br.com.seguros.entidades.Apolice;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeguradoSemCpf {
    private Long id;
    private String nome;
    private List<Apolice> apolices = new ArrayList<>();
}
