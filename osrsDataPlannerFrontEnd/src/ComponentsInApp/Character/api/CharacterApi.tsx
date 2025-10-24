import axios from "axios";

export const HighscoresEndpoint: Record<string, string> = {
    NORMAL: "https://services.runescape.com/m=hiscore_oldschool/index_lite.json",
    IRONMAN: "https://services.runescape.com/m=hiscore_oldschool_ironman/index_lite.json",
    HARDCORE_IRONMAN: "https://services.runescape.com/m=hiscore_oldschool_hardcore_ironman/index_lite.json",
    ULTIMATE_IRONMAN: "https://services.runescape.com/m=hiscore_oldschool_ultimate/index_lite.json",
    DEADMAN: "https://services.runescape.com/m=hiscore_oldschool_deadman/index_lite.json",
    SEASONAL: "https://services.runescape.com/m=hiscore_oldschool_seasonal/index_lite.json",
    TOURNAMENT: "https://services.runescape.com/m=hiscore_oldschool_tournament/index_lite.json",
    FRESH_START_WORLD: "https://secure.runescape.com/m=hiscore_oldschool_fresh_start/index_lite.json",
    PURE: "https://secure.runescape.com/m=hiscore_oldschool_skiller_defence/index_lite.json",
    LEVEL_3_SKILLER: "https://secure.runescape.com/m=hiscore_oldschool_skiller/index_lite.json",
};

const SKILL_NAMES = [
    "Overall", "Attack", "Defence", "Strength", "Hitpoints", "Ranged",
    "Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing",
    "Firemaking", "Crafting", "Smithing", "Mining", "Herblore", "Agility",
    "Thieving", "Slayer", "Farming", "Runecraft", "Hunter", "Construction"
];

export async function fetchHighscores(username: string, accountType: string) {
    const endpoint = HighscoresEndpoint[accountType] || HighscoresEndpoint.NORMAL;
    const url = `${endpoint}?player=${encodeURIComponent(username)}`;
    const response = await axios.get(url);

    // API returns CSV lines — map into structured data
    const rows = response.data.trim().split("\n");
    return SKILL_NAMES.map((name, i) => {
        const [level, xp] = rows[i].split(",");
        return { name, level: Number(level), xp: Number(xp) };
    });
}
