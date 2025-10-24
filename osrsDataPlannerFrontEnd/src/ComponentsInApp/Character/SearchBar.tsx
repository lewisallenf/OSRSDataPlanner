import React, { useState } from "react";

interface Props {
    onSearch: (username: string, accountType: string) => void;
}

export default function SearchBar({ onSearch }: Props) {
    const [username, setUsername] = useState("");
    const [accountType, setAccountType] = useState("NORMAL");

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (username.trim()) {
            onSearch(username, accountType);
            setUsername("");
        }
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="flex flex-col md:flex-row items-center justify-center gap-3"
        >
            <input
                type="text"
                placeholder="Enter OSRS username..."
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className="bg-osrsDark border border-gray-600 text-gray-100 rounded-lg px-4 py-2 w-72 focus:ring-2 focus:ring-osrsBlue transition"
            />
            <select
                value={accountType}
                onChange={(e) => setAccountType(e.target.value)}
                className="bg-osrsDark border border-gray-600 text-gray-200 rounded-lg px-3 py-2 focus:ring-2 focus:ring-osrsBlue"
            >
                <option value="NORMAL">Normal</option>
                <option value="IRONMAN">Ironman</option>
                <option value="HARDCORE_IRONMAN">Hardcore Ironman</option>
                <option value="ULTIMATE_IRONMAN">Ultimate Ironman</option>
            </select>
            <button
                type="submit"
                className="bg-gradient-to-r from-osrsBlue to-blue-500 px-5 py-2 rounded-lg text-white font-bold shadow-md hover:shadow-lg hover:scale-105 transition-transform"
            >
                Search
            </button>
        </form>
    );
}

