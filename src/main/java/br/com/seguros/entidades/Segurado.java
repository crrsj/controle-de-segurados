package br.com.seguros.entidades;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_segurados")
@Data
public class Segurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @CPF
    private String cpf;
    @OneToMany(mappedBy = "segurado", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Apolice>apolices = new ArrayList<>();
}
