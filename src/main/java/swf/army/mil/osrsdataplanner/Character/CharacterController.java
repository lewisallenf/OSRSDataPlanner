package swf.army.mil.osrsdataplanner.Character;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterServiceForController;

    public CharacterController(CharacterService characterServiceForController) {
        this.characterServiceForController = characterServiceForController;
    }

    /// ////////////////////////////////CRUD ENDPOINTS//////////////////////////////////////////

    @GetMapping
    public ResponseEntity<List<CharacterEntity>> getAllCharacters() {
        return ResponseEntity.ok(characterServiceForController.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterEntity> getCharacterById(@PathVariable Long id) {
        CharacterEntity character = characterServiceForController.getById(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(character);
    }

    @PostMapping
    public ResponseEntity<CharacterEntity> createCharacter(@RequestBody CharacterEntity character){
        CharacterEntity savedCharacter = characterServiceForController.create(character);
        return ResponseEntity.ok(savedCharacter);
    }


}

