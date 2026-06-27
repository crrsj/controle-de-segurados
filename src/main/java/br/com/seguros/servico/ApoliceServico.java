package br.com.seguros.servico;

import br.com.seguros.dtos.ApoliceDto;
import br.com.seguros.entidades.Apolice;
import br.com.seguros.excecoes.ExcecaoSeguradoNaoEncontrado;
import br.com.seguros.repositorio.ApoliceRepositorio;
import br.com.seguros.repositorio.SeguradoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApoliceServico {

    private final ApoliceRepositorio apoliceRepositorio;
    private final SeguradoRepositorio seguradoRepositorio;
    private final ModelMapper modelMapper;


    @Transactional
    public ApoliceDto salvarApolice(Long seguradoId,ApoliceDto apoliceDto){
        var segurado = seguradoRepositorio.findById(seguradoId).orElseThrow(()->
                new ExcecaoSeguradoNaoEncontrado("Segurado não localizado !"));
        var apolice = modelMapper.map(apoliceDto, Apolice.class);
        apolice.setSegurado(segurado);
       var novaApolice = apoliceRepositorio.save(apolice);
       return modelMapper.map(novaApolice, ApoliceDto.class);
    }
}
