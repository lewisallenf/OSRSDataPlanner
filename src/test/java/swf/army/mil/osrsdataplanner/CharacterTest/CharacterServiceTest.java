package swf.army.mil.osrsdataplanner.CharacterTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swf.army.mil.osrsdataplanner.Character.CharacterEntity;
import swf.army.mil.osrsdataplanner.Character.CharacterRepository;
import swf.army.mil.osrsdataplanner.Character.CharacterService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock // Mock repository to isolate service logic
    private CharacterRepository characterRepositoryForServiceTest;

    @InjectMocks // Inject mocked repository into the service
    private CharacterService characterServiceForServiceTest;
    //Uses the array in CharacterEntity to create the array character
    private CharacterEntity[] characters;

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
    void shouldCallRepositoryAndGetAllCharacters() {
//        //Needs a Character for the testing, once multiple uses it, do a @BeforeEach
//        characters = new CharacterEntity[]
//        {new CharacterEntity(1L, "Hero", 50.0,12345.0, Timestamp.from(Instant.now()))};
//        //Arrange: prepare the repository to return a list with the character
        when(characterRepositoryForServiceTest.findAll()).thenReturn(Arrays.asList(characters));
        //Act: call the service method
        List<CharacterEntity> result = characterServiceForServiceTest.getAll();
        //Assert: Verify results
        assertEquals(3, result.size());
        // Verify(Optional)
        verify(characterRepositoryForServiceTest, times(1)).findAll(); // Verify findAll() called once
    }

    @Test
    void shouldGetCharacterById(){
        //Arrange, when findById(2L) is called, you return Option of 1 character
        when(characterRepositoryForServiceTest.findById(2L)).thenReturn(Optional.of(characters[1]));
        //Act, result should be 2L
        CharacterEntity result = characterServiceForServiceTest.getById(2L);
        //Assert, for result make sure its not null, extra verification
        assertNotNull(result, "Returned character should not be null");
        //Extra verification in Assert
        assertEquals("Zezima", result.getUsername(), "Username should be Zezima");
        assertEquals(60, result.getCombatLevel(), "Combat level should be 60");
        // Verify(Optional)
        verify(characterRepositoryForServiceTest, times(1)).findById(2L);

    }
    @Test
    void shouldCreateCharacter(){
        CharacterEntity newestCharacter = new CharacterEntity(null, "Zamarak", 70, 7000.0, Timestamp.from(Instant.now()));
        //Arrange
        when(characterRepositoryForServiceTest.save(any(CharacterEntity.class))).thenReturn(newestCharacter);
        //Act
        CharacterEntity result = characterServiceForServiceTest.create(newestCharacter);
        //Assert
        assertNotNull(result);
        assertEquals("Zamarak", result.getUsername());
        // Verify(Optional)
        verify(characterRepositoryForServiceTest, times(1)).save(newestCharacter);
    }
    @Test
    void shouldUpdateCharacter(){
        //Arrange
        //Act
        //Assert
        // Verify(Optional)
    }
    @Test
    void shouldDeleteCharacter(){
        //Arrange
        //Act
        //Assert
        // Verify(Optional)
    }
}
