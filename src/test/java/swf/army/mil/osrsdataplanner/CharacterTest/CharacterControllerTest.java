package swf.army.mil.osrsdataplanner.CharacterTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import swf.army.mil.osrsdataplanner.Character.CharacterController;
import swf.army.mil.osrsdataplanner.Character.CharacterEntity;
import swf.army.mil.osrsdataplanner.Character.CharacterService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;  // Simulates HTTP requests

    @MockitoBean
    private CharacterService mockCharacterService;

    @Autowired
    private ObjectMapper objectMapper;  // Converts Java objects <-> JSON

    private CharacterEntity[] characters; // Test data Array


    @BeforeEach
    public void setUp() {
        // ARRANGE: Create multiple test characters
        characters = new CharacterEntity[]{
                new CharacterEntity(1L,"Hero", 50.0, 12345.0, Timestamp.from(Instant.now())),
                new CharacterEntity(2L,"Zezima", 60, 20000.0, Timestamp.from(Instant.now())),
                new CharacterEntity(3L,"Devil", 45, 15000.5, Timestamp.from(Instant.now()))
        };
    }

    @Test
    void shouldReturnAllCharacters() throws Exception {
        // ARRANGE
        List<CharacterEntity> allCharacters = Arrays.asList(characters);
        when(mockCharacterService.getAll()).thenReturn(allCharacters);

        // ACT  and ASSERT
        mockMvc.perform(get("/api/characters")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk()) // HTTP 200
                .andExpect(jsonPath("$.length()").value(3)); // 3 characters in response

        verify(mockCharacterService, times(1)).getAll(); // Verify service call
    }
    @Test
    void shouldReturnCharacterById() throws Exception{
        //Arrange
        when(mockCharacterService.getById(2L)).thenReturn((characters[1]));
        //Act and Assert
        mockMvc.perform(get("/api/characters/2")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Zezima"))
                .andExpect(jsonPath( "$.combatLevel").value(60));
        //Verify
        verify(mockCharacterService, times(1)).getById(2L);

    }

    @Test
    void shouldCreateCharacter() throws Exception{
        CharacterEntity newestCharacter = new CharacterEntity(null, "Zamarak", 70, 7000.0, Timestamp.from(Instant.now()));
        //Arrange
        when(mockCharacterService.create(any(CharacterEntity.class))).thenReturn(newestCharacter);
        //Act & Assert
        mockMvc.perform(post("/api/characters")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(newestCharacter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Zamarak"))
                .andExpect(jsonPath("$.combatLevel").value(70));
        // Verify(Optional)
        verify(mockCharacterService, times(1)).create(any(CharacterEntity.class));
    }
    @Test
    void shouldUpdateCharacter() throws Exception{
        CharacterEntity updatedCharacter = new CharacterEntity(1L, "updatedHero", 75, 50000.0, Timestamp.from(Instant.now()));
        //Arrange
        when(mockCharacterService.update(eq(1L), any(CharacterEntity.class))).thenReturn(updatedCharacter);
        //Act&Assert
        mockMvc.perform(put("/api/characters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCharacter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedHero"))
                .andExpect(jsonPath("$.combatLevel").value(75));

        verify(mockCharacterService, times(1)).update(eq(1L), any(CharacterEntity.class));
    }
    @Test
    void shouldDeleteCharacter() throws Exception {
        //Arrange
        when(mockCharacterService.delete(1L)).thenReturn(true);
        //Act
        //Assert
        mockMvc.perform(delete("/api/characters/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        // Verify(Optional)
        verify(mockCharacterService, times(1)).delete(1L);
    }

}
