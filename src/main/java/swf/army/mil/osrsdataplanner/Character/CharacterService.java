package swf.army.mil.osrsdataplanner.Character;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepositoryForService;

    public CharacterService(CharacterRepository characterRepositoryForService){
        this.characterRepositoryForService = characterRepositoryForService;
    }
    /// ///////////////////////////////REQUESTS///////////////////////////////////////////////////////////

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

    public CharacterEntity update(Long id, CharacterEntity updatedCharacter){
        return characterRepositoryForService.findById(id)
                .map(existing ->{
                    existing.setUsername(updatedCharacter.getUsername());
                    existing.setCombatLevel(updatedCharacter.getCombatLevel());
                    existing.setTotalXP(updatedCharacter.getTotalXP());
                    existing.setRequestCreated(updatedCharacter.getRequestCreated());
                    return characterRepositoryForService.save(existing);
                })
                .orElse(null);
    }

    public boolean delete(Long id){
        if(!characterRepositoryForService.existsById(id)){
            return false;
        }
        characterRepositoryForService.deleteById(id);
        return true;
    }
}

