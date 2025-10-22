package swf.army.mil.osrsdataplanner.Character;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepositoryForService;

    public CharacterService(CharacterRepository characterRepositoryForService){
        this.characterRepositoryForService = characterRepositoryForService;
    }

    public List<CharacterEntity> getAll() {
       return characterRepositoryForService.findAll();
    }

    public CharacterEntity getById(Long id){
        return characterRepositoryForService.findById(id)
                .orElse(null);
    }

    public CharacterEntity create(CharacterEntity character) {
        return characterRepositoryForService.save(character);
    }
}

