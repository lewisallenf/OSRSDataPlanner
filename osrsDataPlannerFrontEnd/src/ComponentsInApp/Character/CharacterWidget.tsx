import { useState } from "react";
import CharacterStatsTable from "./CharacterStatsTable";

export default function CharacterWidget({ character, onDelete }: any) {
    const [expanded, setExpanded] = useState(false);

    return (
        <div className="bg-white rounded-xl shadow p-4 w-full max-w-2xl border">
            <div
                className="flex justify-between items-center cursor-pointer"
                onClick={() => setExpanded(!expanded)}
            >
                <h2 className="text-lg font-semibold">
                    {expanded ? "▼" : "►"} {character.username}
                </h2>
                <p className="text-sm text-gray-500">{character.accountType}</p>
            </div>

            {expanded && (
                <div className="mt-3">
                    <CharacterStatsTable skills={character.skills} />
                    <button
                        onClick={() => onDelete(character.id)}
                        className="bg-red-500 text-white px-3 py-1 rounded-md mt-2 hover:bg-red-600"
                    >
                        Delete
                    </button>
                </div>
            )}
        </div>
    );
}
