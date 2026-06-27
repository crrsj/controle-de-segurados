package br.com.seguros.servico;

import br.com.seguros.repositorio.ApoliceRepositorio;
import br.com.seguros.repositorio.SeguradoRepositorio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeguradoServico {

    private final SeguradoRepositorio seguradoRepositorio;
    private final ApoliceRepositorio apoliceRepositorio;
    private final ModelMapper modelMapper;
}
