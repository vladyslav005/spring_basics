package idk.controller;


import idk.dto.UpdateRequest;
import idk.entity.Note;
import idk.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/list")
    public String listAllNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "list-all";
    }



    @GetMapping("/delete/{id}")
    public String deleteNote(Note note, @PathVariable String id) {
        long longId = Long.parseLong(id);
        System.out.println(longId);
        noteService.deleteById(longId);
        return "redirect:/notes/list";
    }



    @GetMapping("/edit")
    public String editNote(@RequestParam String id, Model model) {
        model.addAttribute("note", noteService.getById(Long.parseLong(id)));

        return "edit-note";
    }


    @PostMapping("/edit")
    public String updateNote(UpdateRequest note) {

        noteService.update(new Note(note.getId(), note.getTitle(), note.getContent()));
        return "redirect:/notes/list";
    }
}
