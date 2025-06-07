package com.lgsoftworks.infrastructure.rest.controller;

import com.lgsoftworks.domain.dto.PlayerDTO;
import com.lgsoftworks.domain.dto.summary.TeamSummaryDTO;
import com.lgsoftworks.domain.enums.DocumentType;
import com.lgsoftworks.domain.port.in.PlayerUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    private final String urlPlayer = "/api/v1/person/player";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerUseCase playerUseCase;

    @Test
    @DisplayName("Debe devolver la lista de jugadores correctamente")
    void shouldReturnListOfPlayers() throws Exception {
        // Arrange
        PlayerDTO player = new PlayerDTO();
        player.setId(1L);
        player.setFirstName("Juan");
        player.setLastName("Pérez");
        player.setCity("Bogotá");
        player.setAge((byte) 25);
        player.setDocumentType(DocumentType.CC);
        player.setDocumentNumber("123456789");
        player.setEmail("juan.perez@example.com");

        TeamSummaryDTO team = new TeamSummaryDTO();
        team.setId(1L);
        team.setName("Los Tigres");
        player.setTeam(team);

        List<PlayerDTO> players = List.of(player);
        when(playerUseCase.findAll()).thenReturn(players);

        // Act & Assert
        mockMvc.perform(get(urlPlayer) // asegúrate que esta sea la ruta correcta del @GetMapping
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].firstName").value("Juan"))
                .andExpect(jsonPath("$[0].team.name").value("Los Tigres"));
    }

    @Test
    @DisplayName("Debe devolver el jugador por ID correctamente")
    void shouldReturnPlayerById() throws Exception {
        Long playerId = 1L;

        PlayerDTO player = new PlayerDTO();
        player.setId(playerId);
        player.setFirstName("Luis");
        player.setLastName("Gomez");
        player.setAge((byte) 25);
        player.setCity("Bogotá");
        player.setDocumentType(DocumentType.CC);
        player.setDocumentNumber("1234567890");
        player.setEmail("luis@example.com");

        when(playerUseCase.findById(playerId)).thenReturn(Optional.of(player));

        mockMvc.perform(get(urlPlayer + "/{id}", playerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(playerId))
                .andExpect(jsonPath("$.firstName").value("Luis"))
                .andExpect(jsonPath("$.lastName").value("Gomez"));
    }

}
