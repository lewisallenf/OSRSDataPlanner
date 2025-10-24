import { useState } from "react";
import SearchBar from "../Character/SearchBar";
import CharacterWidget from "../Character/CharacterWidget";
import { fetchHighscores } from "../Character/api/CharacterApi";

interface CharacterData {
    id: string;
    username: string;
    accountType: string;
    skills: { name: string; level: number; xp: number }[];
}

export default function Home() {
    const [characters, setCharacters] = useState<CharacterData[]>([]);
    const [hasSearched, setHasSearched] = useState(false);

    const handleSearch = async (username: string, accountType: string) => {
        const skills = await fetchHighscores(username, accountType);
        const newChar: CharacterData = {
            id: crypto.randomUUID(),
            username,
            accountType,
            skills,
        };
        setCharacters((prev) => [newChar, ...prev]);
        setHasSearched(true);
    };

    const handleDelete = (id: string) => {
        setCharacters((prev) => prev.filter((c) => c.id !== id));
    };

    return (
        <div className={"App"}>
            {!hasSearched && characters.length === 0 && (
                <div style={{textAlign: 'center'}}>
                    <h1 className="heading">
                        🧙‍♂️ OSRS Data Planner
                    </h1>
                    <p className={"SubTitle"}>
                        Search for your Old School RuneScape character to view their stats, levels, and experience.
                    </p>
                    <SearchBar onSearch={handleSearch} size="large" />
                </div>
            )}

            {hasSearched && (
                <div>
                    <SearchBar onSearch={handleSearch} size="large" />
                    <div >
                        {characters.map((char) => (
                            <CharacterWidget key={char.id} character={char} onDelete={handleDelete} />
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
}
