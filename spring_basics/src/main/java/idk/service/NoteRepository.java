package idk.service;

import idk.entity.Note;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NoteRepository implements NoteService {

    private final Set<Long> ids = new HashSet<>();
    private final List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> listAll() {
        return notes;
    }

    @Override
    public Note add(Note note) {
        if (ids.contains(note.getId()))
            throw new RuntimeException("Duplicate id");

        notes.add(note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        notes.remove(getById(id));
    }

    @Override
    public void update(Note note) {
        notes.replaceAll((note1) -> Objects.equals(note1.getId(), note.getId()) ? note : note1);
    }

    @Override
    public Note getById(long id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }

        return null;
    }
}
