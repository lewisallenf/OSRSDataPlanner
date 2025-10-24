export default function CharacterStatsTable({ skills }: any) {
    return (
        <div className="bg-osrsDark rounded-lg p-3 border border-gray-700 mt-3">
            <table className="w-full text-sm text-gray-200">
                <thead>
                <tr className="text-osrsGold border-b border-gray-600">
                    <th className="py-2 text-left">Skill</th>
                    <th className="py-2 text-left">Level</th>
                    <th className="py-2 text-left">XP</th>
                </tr>
                </thead>
                <tbody>
                {skills.map((s: any) => (
                    <tr
                        key={s.name}
                        className="hover:bg-osrsAccent transition border-b border-gray-700"
                    >
                        <td className="py-1">{s.name}</td>
                        <td>{s.level}</td>
                        <td>{s.xp.toLocaleString()}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
