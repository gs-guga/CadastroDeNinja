package dev.java10x.CadastroDeNinjas.Tests;


import dev.java10x.CadastroDeNinjas.Ninjas.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NinjasServiceTest {

    @Mock
    private NinjaRepository ninjaRepository;

    @Mock
    private NinjaMapper ninjaMapper;

    @InjectMocks
    private NinjasService ninjasService;

    @Test
    @DisplayName("Deve listar todos os ninjas com sucesso")
    void deveListarTodosOsNinjasComSucesso() {
        // Arrange
        NinjaModel ninjaModel = new NinjaModel();
        NinjaDTO ninjaDTO = new NinjaDTO();

        when(ninjaRepository.findAll()).thenReturn(List.of(ninjaModel));
        when(ninjaMapper.map(ninjaModel)).thenReturn(ninjaDTO);

        // Act
        List<NinjaDTO> resultado = ninjasService.listarNinja();

        // Assert
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(1);
        verify(ninjaRepository, times(1)).findAll();
        verify(ninjaMapper, times(1)).map(ninjaModel);
    }

    @Test
    @DisplayName("Deve retornar um ninja por ID quando ele existir")
    void deveRetornarNinjaPorIdQuandoExistir() {
        // Arrange
        Long id = 1L;
        NinjaModel ninjaModel = new NinjaModel();
        NinjaDTO ninjaDTO = new NinjaDTO();

        when(ninjaRepository.findById(id)).thenReturn(Optional.of(ninjaModel));
        when(ninjaMapper.map(ninjaModel)).thenReturn(ninjaDTO);

        // Act
        NinjaDTO resultado = ninjasService.listarID(id);

        // Assert
        assertThat(resultado).isNotNull();
        verify(ninjaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar null ao buscar ID inexistente")
    void deveRetornarNullQuandoBuscarIdInexistente() {
        // Arrange
        Long id = 99L;
        when(ninjaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        NinjaDTO resultado = ninjasService.listarID(id);

        // Assert
        assertThat(resultado).isNull();
        verify(ninjaRepository, times(1)).findById(id);
        verifyNoInteractions(ninjaMapper); // Garante que o mapper nem foi chamado
    }

    @Test
    @DisplayName("Deve criar um novo ninja com sucesso")
    void deveCriarNinjaComSucesso() {
        // Arrange
        NinjaDTO inputDTO = new NinjaDTO();
        NinjaModel ninjaModel = new NinjaModel();
        NinjaModel ninjaSalvo = new NinjaModel();
        NinjaDTO outputDTO = new NinjaDTO();

        when(ninjaMapper.map(inputDTO)).thenReturn(ninjaModel);
        when(ninjaRepository.save(ninjaModel)).thenReturn(ninjaSalvo);
        when(ninjaMapper.map(ninjaSalvo)).thenReturn(outputDTO);

        // Act
        NinjaDTO resultado = ninjasService.criarNinja(inputDTO);

        // Assert
        assertThat(resultado).isNotNull();
        verify(ninjaRepository, times(1)).save(ninjaModel);
    }

    @Test
    @DisplayName("Deve atualizar um ninja quando o ID existir")
    void deveAtualizarNinjaQuandoExistir() {
        // Arrange
        Long id = 1L;
        NinjaDTO inputDTO = new NinjaDTO();
        NinjaModel ninjaExistente = new NinjaModel();
        NinjaModel ninjaMapeado = new NinjaModel();
        NinjaModel ninjaSalvo = new NinjaModel();
        NinjaDTO outputDTO = new NinjaDTO();

        when(ninjaRepository.findById(id)).thenReturn(Optional.of(ninjaExistente));
        when(ninjaMapper.map(inputDTO)).thenReturn(ninjaMapeado);
        when(ninjaRepository.save(ninjaMapeado)).thenReturn(ninjaSalvo);
        when(ninjaMapper.map(ninjaSalvo)).thenReturn(outputDTO);

        // Act
        NinjaDTO resultado = ninjasService.atualizarNinja(id, inputDTO);

        // Assert
        assertThat(resultado).isNotNull();
        verify(ninjaRepository, times(1)).findById(id);
        verify(ninjaRepository, times(1)).save(ninjaMapeado);
    }

    @Test
    @DisplayName("Deve deletar ninja por ID com sucesso")
    void deveDeletarNinjaComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(ninjaRepository).deleteById(id);

        // Act
        ninjasService.deletarNinja(id);

        // Assert
        verify(ninjaRepository, times(1)).deleteById(id);
    }
}