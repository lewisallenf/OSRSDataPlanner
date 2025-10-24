import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import Home from "../Pages/Home.tsx"

describe("Home page components", () =>{
    it('should display the title', () =>{
        render(<Home/>)
        expect(screen.getByRole("heading", {name: /🧙‍♂️ OSRS Data Planner/i}))
    })
    it('should display the subtitle', () =>{
        render(<Home/>)
        expect(screen.getByText(/Search for your Old School RuneScape character to view their stats, levels, and experience./i))
            .toBeVisible();
    })
    it("should render the SearchBar component", () =>{
        render(<Home/>)
        expect(screen.getByRole("heading", {name: /Mocked SearchBar/i})).toBeVisible();
        expect(screen.getByRole("button", {name: /search/i})).toBeVisible();
    })
})