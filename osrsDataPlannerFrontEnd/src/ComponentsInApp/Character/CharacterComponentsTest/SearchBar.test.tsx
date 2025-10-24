import {render, screen} from "@testing-library/react";
import { describe, expect, it } from "vitest";
import SearchBar from "../SearchBar";



describe('SearchBar Component', () => {
    it('should display the title', () => {
        //Insert what you need/ render the CharacterSection.tsx
        render(<SearchBar/>)
        //Expect what?
        expect(screen.getByPlaceholderText("Enter OSRS username...")).toBeVisible();
    })
    it('should display a select',()=>{
        render(<SearchBar/>)
        expect(screen.getByRole('combobox')).toBeVisible();
    })
    it('should display a button',()=>{
        render(<SearchBar/>)
        expect(screen.getByRole('button')).toBeVisible();
    })
})





















// it('should not show "add todo" modal on load', () => {
//     render(<TodoPage/>);
//     expect(screen.queryByRole("dialog", {name: "Add Todo Dialog"})).toBeNull();
// })
//
// it('should display "add todo" modal when add button is clicked', async () => {
//     const user = userEvent.setup();
//     render(<TodoPage/>);
//     await user.click(screen.getByRole("button", { name: "Add Todo"}));
//     expect(screen.getByRole("dialog", { name: "Add Todo Dialog"})).toBeVisible();
// })
//
// it('should close "add todo" modal when close button is clicked', async () => {
//     const user = userEvent.setup();
//     render(<TodoPage/>);
//     await user.click(screen.getByRole("button", { name: "Add Todo"}));
//     await user.click(screen.getByRole("button", { name: "Close"}));
//     expect(screen.queryByRole("dialog", { name: "Add Todo Dialog"})).toBeNull();
// })
//
// it('should display all todos', async () => {
//     vi.spyOn(TodoClient, 'getTodos').mockResolvedValueOnce([{id: 1, name: "test 1", status: "incomplete"}, {id: 2, name: "test 2", status: "incomplete"}]);
//     render(<TodoPage/>);
//     expect(await screen.findAllByRole("listitem")).toHaveLength(2);
// })
//
// it('should toggle a todo when clicked', async () => {
//     vi.spyOn(TodoClient, 'getTodos').mockResolvedValueOnce([{id: 1, name: "test 1", status: "incomplete"}]);
//     render(<TodoPage/>);
//     const user = userEvent.setup();
//     const todo = await screen.findByRole("listitem", {name: "test 1"});
//     const markCompleteButton = within(todo).getByRole("button", {name: "Mark Complete"});
//     await act(async () => {
//         await user.click(markCompleteButton);
//     })
//     expect(await within(todo).findByRole("button", { name: "Mark Incomplete"})).toBeVisible();
// })