package br.com.seguros.servico;

import br.com.seguros.dtos.SeguradoDto;
import br.com.seguros.dtos.SeguradoSemCpf;
import br.com.seguros.entidades.Segurado;
import br.com.seguros.repositorio.SeguradoRepositorio;
import br.com.seguros.excecoes.ExcecaoSeguradoNaoEncontrado;
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

    public Segurado retornaseguradoOuLancaExcessao(Long id){
        return seguradoRepositorio.findById(id).orElseThrow(()->
                new ExcecaoSeguradoNaoEncontrado("Segurado não encontrado !"));
    }


    public SeguradoSemCpf buscarPorCpf(String cpf){
        var segurado = seguradoRepositorio.findByCpf(cpf).orElseThrow(() ->
                new ExcecaoSeguradoNaoEncontrado("Segurado com o CPF " + cpf + " não foi encontrado."));
        return modelMapper.map(segurado,SeguradoSemCpf.class);
    }

    public List<SeguradoSemCpf> buscarPorNome(String nome) {
        List<Segurado> segurados = seguradoRepositorio.findByNomeContainingIgnoreCase(nome);
        return segurados.stream()
                .map(segurado -> modelMapper.map(segurado, SeguradoSemCpf.class))
                .toList();
    }


    @Transactional
    public SeguradoSemCpf atualizarSegurado(Long id, SeguradoSemCpf seguradoSemCpf){
        var segurado = retornaseguradoOuLancaExcessao(id);
        modelMapper.map(seguradoSemCpf,segurado);
        return modelMapper.map(segurado, SeguradoSemCpf.class);
}

    public SeguradoSemCpf buscarSeguradoPorId(Long id){
        var segurado = retornaseguradoOuLancaExcessao(id);
        return modelMapper.map(segurado, SeguradoSemCpf.class);

   }

   @Transactional
   public void excluirSegurado(Long id){
        var segurado = retornaseguradoOuLancaExcessao(id);
        seguradoRepositorio.delete(segurado);
   }
}

