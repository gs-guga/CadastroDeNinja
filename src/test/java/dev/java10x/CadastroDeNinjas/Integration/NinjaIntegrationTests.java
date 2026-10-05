package dev.java10x.CadastroDeNinjas.Integration;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaRepository;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjasService;
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
class NinjaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NinjasService ninjasService;

    @Autowired
    private NinjaRepository ninjaRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Limpa o banco H2 antes de cada teste para garantir isolamento
        ninjaRepository.deleteAll();
    }


    private NinjaDTO popularNinjaNoBanco() {
        NinjaDTO dto = new NinjaDTO();
        dto.setNome("Kakashi Hatake");
        dto.setEmail("kakashi@konoha.com");
        dto.setIdade(30);
        dto.setRank("Jounin");
        // Usamos o próprio service para gravar no banco e retornar o objeto com o ID gerado
        return ninjasService.criarNinja(dto);
    }

    // ==========================================
    // TESTES: BOAS VINDAS
    // ==========================================
    @Test
    @DisplayName("GET /ninjas/boasVindas - Deve retornar a mensagem de boas vindas")
    void deveExibirBoasVindas() throws Exception {
        mockMvc.perform(get("/ninjas/boasVindas"))
                .andExpect(status().isOk())
                .andExpect(content().string("Boas vindas"));
    }

    // ==========================================
    // TESTES: CREATE (POST)
    // ==========================================
    @Test
    @DisplayName("POST /ninjas/criar - Deve criar o ninja com sucesso (HTTP 201)")
    void deveCriarNinjaEGravarNoBanco() throws Exception {
        NinjaDTO novoNinja = new NinjaDTO();
        novoNinja.setNome("Naruto Uzumaki");
        novoNinja.setEmail("naruto@konoha.com");
        novoNinja.setIdade(17);
        novoNinja.setRank("Gennin");

        mockMvc.perform(post("/ninjas/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novoNinja)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Ninja adicionado com sucesso!"));
    }

    // ==========================================
    // TESTES: READ (GET)
    // ==========================================
    @Test
    @DisplayName("GET /ninjas/listar - Deve listar todos os ninjas cadastrados (HTTP 200)")
    void deveListarTodosOsNinjas() throws Exception {
        popularNinjaNoBanco(); // Garante que tem pelo menos 1 ninja no banco

        mockMvc.perform(get("/ninjas/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()) // Valida se o retorno é uma lista (array JSON)
                .andExpect(jsonPath("$[0].nome").value("Kakashi Hatake"));
    }

    @Test
    @DisplayName("GET /ninjas/listar/{id} - Deve encontrar um ninja existente por ID (HTTP 200)")
    void deveBuscarNinjaPorIdComSucesso() throws Exception {
        NinjaDTO ninjaSalvo = popularNinjaNoBanco();

        mockMvc.perform(get("/ninjas/listar/" + ninjaSalvo.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Kakashi Hatake"))
                .andExpect(jsonPath("$.email").value("kakashi@konoha.com"));
    }

    @Test
    @DisplayName("GET /ninjas/listar/{id} - Deve retornar 404 ao buscar ID inexistente")
    void deveRetornar404AoBuscarNinjaInexistente() throws Exception {
        mockMvc.perform(get("/ninjas/listar/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Ninja não encontrado!"));
    }

    // ==========================================
    // TESTES: UPDATE (PUT)
    // ==========================================
    @Test
    @DisplayName("PUT /ninjas/alterar/{id} - Deve alterar dados de um ninja existente (HTTP 200)")
    void deveAlterarNinjaComSucesso() throws Exception {
        NinjaDTO ninjaSalvo = popularNinjaNoBanco();

        // Modificando os dados para o update
        ninjaSalvo.setNome("Kakashi Hokage");
        ninjaSalvo.setRank("Hokage");

        mockMvc.perform(put("/ninjas/alterar/" + ninjaSalvo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ninjaSalvo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Kakashi Hokage"))
                .andExpect(jsonPath("$.rank").value("Hokage"));
    }

    @Test
    @DisplayName("PUT /ninjas/alterar/{id} - Deve retornar 404 ao alterar ID inexistente")
    void deveRetornar404AoAlterarNinjaInexistente() throws Exception {
        NinjaDTO dadosAtualizacao = new NinjaDTO();
        dadosAtualizacao.setNome("Ninja Fantasma");

        mockMvc.perform(put("/ninjas/alterar/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosAtualizacao)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Ninja não encontrado!"));
    }

    // ==========================================
    // TESTES: DELETE (DELETE)
    // ==========================================
    @Test
    @DisplayName("DELETE /ninjas/deletar/{id} - Deve deletar um ninja existente (HTTP 200)")
    void deveDeletarNinjaComSucesso() throws Exception {
        NinjaDTO ninjaSalvo = popularNinjaNoBanco();
        Long id = ninjaSalvo.getId();

        mockMvc.perform(delete("/ninjas/deletar/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Ninja com o id " + id + " deletado com sucesso!"));
    }

    @Test
    @DisplayName("DELETE /ninjas/deletar/{id} - Deve retornar 404 ao deletar ID inexistente")
    void deveRetornar404AoDeletarNinjaInexistente() throws Exception {
        mockMvc.perform(delete("/ninjas/deletar/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Ninja não encontrado"));
    }
}


