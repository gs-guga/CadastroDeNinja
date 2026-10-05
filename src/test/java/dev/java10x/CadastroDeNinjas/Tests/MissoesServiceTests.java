package dev.java10x.CadastroDeNinjas.Tests;


import dev.java10x.CadastroDeNinjas.Missoes.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MissoesServiceTest {

    @Mock
    private MissoesRepository missoesRepository;

    @Mock
    private MissoesMapper missoesMapper;

    @InjectMocks
    private MissoesService missoesService;

    @Test
    @DisplayName("Deve listar todas as missões com sucesso")
    void deveListarTodasAsMissoesComSucesso() {
        // Arrange
        MissoesModel missaoModel = new MissoesModel();
        MissoesDTO missaoDTO = new MissoesDTO();

        when(missoesRepository.findAll()).thenReturn(List.of(missaoModel));
        when(missoesMapper.map(missaoModel)).thenReturn(missaoDTO);

        // Act
        List<MissoesDTO> resultado = missoesService.listarMissoes();

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        verify(missoesRepository, times(1)).findAll();
        verify(missoesMapper, times(1)).map(missaoModel);
    }

    @Test
    @DisplayName("Deve retornar uma missão por ID quando ela existir")
    void deveRetornarMissaoPorIdQuandoExistir() {
        // Arrange
        Long id = 1L;
        MissoesModel missaoModel = new MissoesModel();
        MissoesDTO missaoDTO = new MissoesDTO();

        when(missoesRepository.findById(id)).thenReturn(Optional.of(missaoModel));
        when(missoesMapper.map(missaoModel)).thenReturn(missaoDTO);

        // Act
        MissoesDTO resultado = missoesService.listarPorId(id);

        // Assert
        assertThat(resultado).isNotNull();
        verify(missoesRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar null ao buscar missão por ID inexistente")
    void deveRetornarNullQuandoBuscarIdInexistente() {
        // Arrange
        Long id = 99L;
        when(missoesRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        MissoesDTO resultado = missoesService.listarPorId(id);

        // Assert
        assertThat(resultado).isNull();
        verify(missoesRepository, times(1)).findById(id);
        verifyNoInteractions(missoesMapper);
    }

    @Test
    @DisplayName("Deve criar uma nova missão com sucesso")
    void deveCriarMissaoComSucesso() {
        // Arrange
        MissoesDTO inputDTO = new MissoesDTO();
        MissoesModel missaoModel = new MissoesModel();
        MissoesModel missaoSalva = new MissoesModel();
        MissoesDTO outputDTO = new MissoesDTO();

        when(missoesMapper.map(inputDTO)).thenReturn(missaoModel);
        when(missoesRepository.save(missaoModel)).thenReturn(missaoSalva);
        when(missoesMapper.map(missaoSalva)).thenReturn(outputDTO);

        // Act
        MissoesDTO resultado = missoesService.criarMissoes(inputDTO);

        // Assert
        assertThat(resultado).isNotNull();
        verify(missoesRepository, times(1)).save(missaoModel);
    }

    @Test
    @DisplayName("Deve alterar uma missão quando o ID existir")
    void deveAlterarMissaoQuandoExistir() {
        // Arrange
        Long id = 1L;
        MissoesDTO inputDTO = new MissoesDTO();
        MissoesModel missaoExistente = new MissoesModel();
        MissoesModel missaoMapeada = new MissoesModel();
        MissoesModel missaoSalva = new MissoesModel();
        MissoesDTO outputDTO = new MissoesDTO();

        when(missoesRepository.findById(id)).thenReturn(Optional.of(missaoExistente));
        when(missoesMapper.map(inputDTO)).thenReturn(missaoMapeada);
        when(missoesRepository.save(missaoMapeada)).thenReturn(missaoSalva);
        when(missoesMapper.map(missaoSalva)).thenReturn(outputDTO);

        // Act
        MissoesDTO resultado = missoesService.alterarMissoes(id, inputDTO);

        // Assert
        assertThat(resultado).isNotNull();
        verify(missoesRepository, times(1)).findById(id);
        verify(missoesRepository, times(1)).save(missaoMapeada);
    }

    @Test
    @DisplayName("Deve retornar null ao tentar alterar missão com ID inexistente")
    void deveRetornarNullAoAlterarMissaoInexistente() {
        // Arrange
        Long id = 99L;
        MissoesDTO inputDTO = new MissoesDTO();

        when(missoesRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        MissoesDTO resultado = missoesService.alterarMissoes(id, inputDTO);

        // Assert
        assertThat(resultado).isNull();
        verify(missoesRepository, times(1)).findById(id);
        verify(missoesRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar missão por ID com sucesso")
    void deveDeletarMissaoComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(missoesRepository).deleteById(id);

        // Act
        missoesService.deletarMissoes(id);

        // Assert
        verify(missoesRepository, times(1)).deleteById(id);
    }
}