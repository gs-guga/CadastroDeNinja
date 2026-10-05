package dev.java10x.CadastroDeNinjas.Integration;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesDTO;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesRepository;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class MissoesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MissoesService missoesService;

    @Autowired
    private MissoesRepository missoesRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Limpa o banco H2 antes de cada teste
        missoesRepository.deleteAll();
    }


    private MissoesDTO popularMissaoNoBanco() {
        MissoesDTO dto = new MissoesDTO();
        dto.setNome("Resgatar o Kazekage");
        dto.setRank("Rank S");
        return missoesService.criarMissoes(dto);
    }

    // ==========================================
    // TESTES: CREATE (POST)
    // ==========================================
    @Test
    @DisplayName("POST /missoes/criar - Deve criar uma missão com sucesso (HTTP 201)")
    void deveCriarMissaoEGravarNoBanco() throws Exception {
        MissoesDTO novaMissao = new MissoesDTO();
        novaMissao.setNome("Proteger a Vila da Folha");
        novaMissao.setRank("Rank A");

        mockMvc.perform(
                        post("/missoes/criar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(novaMissao))
                )
                .andExpect(status().isCreated())
                .andExpect(content().string("Missão adicionada com sucesso!"));
    }

    // ==========================================
    // TESTES: READ (GET)
    // ==========================================
    @Test
    @DisplayName("GET /missoes/listar - Deve listar todas as missões registradas (HTTP 200)")
    void deveListarTodasAsMissoes() throws Exception {
        popularMissaoNoBanco();

        mockMvc.perform(
                        get("/missoes/listar")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("Resgatar o Kazekage"))
                .andExpect(jsonPath("$[0].rank").value("Rank S"));
    }

    @Test
    @DisplayName("GET /missoes/listar/{id} - Deve encontrar uma missão por ID (HTTP 200)")
    void deveBuscarMissaoPorIdComSucesso() throws Exception {
        MissoesDTO missaoSalva = popularMissaoNoBanco();

        mockMvc.perform(
                        get("/missoes/listar/" + missaoSalva.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Resgatar o Kazekage"))
                .andExpect(jsonPath("$.rank").value("Rank S"));
    }

    @Test
    @DisplayName("GET /missoes/listar/{id} - Deve retornar 404 ao buscar ID de missão inexistente")
    void deveRetornar404AoBuscarMissaoInexistente() throws Exception {
        mockMvc.perform(
                        get("/missoes/listar/999")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Missão não encontrada!"));
    }

    // ==========================================
    // TESTES: UPDATE (PUT)
    // ==========================================
    @Test
    @DisplayName("PUT /missoes/alterar/{id} - Deve alterar dados de uma missão (HTTP 200)")
    void deveAlterarMissaoComSucesso() throws Exception {
        MissoesDTO missaoSalva = popularMissaoNoBanco();

        missaoSalva.setNome("Resgatar o Kazekage - Concluído");
        missaoSalva.setRank("Rank SS");

        mockMvc.perform(
                        put("/missoes/alterar/" + missaoSalva.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(missaoSalva))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Resgatar o Kazekage - Concluído"))
                .andExpect(jsonPath("$.rank").value("Rank SS"));
    }

    @Test
    @DisplayName("PUT /missoes/alterar/{id} - Deve retornar 404 ao tentar alterar missão inexistente")
    void deveRetornar404AoAlterarMissaoInexistente() throws Exception {
        MissoesDTO dadosAtualizacao = new MissoesDTO();
        dadosAtualizacao.setNome("Missao Fantasma");
        dadosAtualizacao.setRank("Rank C");

        mockMvc.perform(
                        put("/missoes/alterar/999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dadosAtualizacao))
                )
                .andExpect(status().isNotFound())
                .andExpect(content().string("Missão não encontrada!"));
    }

    // ==========================================
    // TESTES: DELETE (DELETE)
    // ==========================================
    @Test
    @DisplayName("DELETE /missoes/deletar/{id} - Deve deletar uma missão existente (HTTP 200)")
    void deveDeletarMissaoComSucesso() throws Exception {
        MissoesDTO missaoSalva = popularMissaoNoBanco();
        Long id = missaoSalva.getId();

        mockMvc.perform(delete("/missoes/deletar/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Missão com o id " + id + " deletada com sucesso!"));
    }

    @Test
    @DisplayName("DELETE /missoes/deletar/{id} - Deve retornar 404 ao deletar missão inexistente")
    void deveRetornar404AoDeletarMissaoInexistente() throws Exception {
        mockMvc.perform(delete("/missoes/deletar/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Missão não encontrada!"));
    }
}
