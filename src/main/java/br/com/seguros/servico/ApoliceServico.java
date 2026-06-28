package br.com.seguros.servico;

import br.com.seguros.dtos.ApoliceDto;
import br.com.seguros.dtos.RelatoriosTotaisDto;
import br.com.seguros.entidades.Apolice;
import br.com.seguros.excecoes.ExcecaoApoliceNaoEncontrada;
import br.com.seguros.excecoes.ExcecaoSeguradoNaoEncontrado;
import br.com.seguros.repositorio.ApoliceRepositorio;
import br.com.seguros.repositorio.RelatoriosTotaisProjecao;
import br.com.seguros.repositorio.SeguradoRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<ApoliceDto>listarApolices(Pageable pageable){
        return apoliceRepositorio.findAll(pageable).map(apolice ->
                modelMapper.map(apolice, ApoliceDto.class)).toList();
    }

    public List<ApoliceDto>listarApolicesAtivas(){
        List<Apolice> apolicesAtivas = apoliceRepositorio.findAllByAtivaTrue();
        return apolicesAtivas.stream().map(apolice->
                modelMapper.map(apolice, ApoliceDto.class)).toList();
    }

    public List<ApoliceDto> listarApolicesInativas() {
        List<Apolice> apolicesInativas = apoliceRepositorio.findAllByAtivaFalse();
        return apolicesInativas.stream()
                .map(apolice -> modelMapper.map(apolice, ApoliceDto.class))
                .toList();
    }

    public Apolice buscarApoliceOuLancarExceCao(Long id){
        return apoliceRepositorio.findById(id).orElseThrow(()->
                new ExcecaoApoliceNaoEncontrada("Apólice não encontrada !"));
    }

    public RelatoriosTotaisDto obterResumoFinanceiroPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data final!");
        }

        // Busca a projeção do banco
        RelatoriosTotaisProjecao projecao = apoliceRepositorio.calcularResumoFinanceiroPorPeriodo(dataInicio, dataFim);

        // Converte e retorna o seu DTO original de forma segura
        return new RelatoriosTotaisDto(
                projecao.getTotalComissao(),
                projecao.getTotalPremioLiquido(),
                projecao.getTotalPremioBruto()
        );
    }
}
