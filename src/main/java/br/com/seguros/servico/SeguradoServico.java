package br.com.seguros.servico;

import br.com.seguros.dtos.SeguradoDto;
import br.com.seguros.dtos.SeguradoSemCpf;
import br.com.seguros.entidades.Segurado;
import br.com.seguros.repositorio.ApoliceRepositorio;
import br.com.seguros.repositorio.SeguradoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeguradoServico {

    private final SeguradoRepositorio seguradoRepositorio;
    private final ModelMapper modelMapper;

    @Transactional
    public SeguradoDto salvarSegurado(SeguradoDto seguradoDto){
        var segurado = modelMapper.map(seguradoDto, Segurado.class);
        var novoSegurado = seguradoRepositorio.save(segurado);
        return modelMapper.map(novoSegurado, SeguradoDto.class);
    }

    public List<SeguradoSemCpf>listarSegurados(Pageable pageable){
        return seguradoRepositorio.findAll(pageable).map(segurado ->
                modelMapper.map(segurado,SeguradoSemCpf.class)).toList();
    }

    public SeguradoSemCpf buscarPorCpf(String cpf){
        var segurado = seguradoRepositorio.findByCpf(cpf).orElseThrow(() ->
                new RuntimeException("Segurado com o CPF " + cpf + " não foi encontrado."));
        return modelMapper.map(segurado,SeguradoSemCpf.class);
    }
}

