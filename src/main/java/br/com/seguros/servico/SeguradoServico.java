package br.com.seguros.servico;

import br.com.seguros.dtos.SeguradoDto;
import br.com.seguros.entidades.Segurado;
import br.com.seguros.repositorio.ApoliceRepositorio;
import br.com.seguros.repositorio.SeguradoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}

