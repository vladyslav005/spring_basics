package idk.service;

import idk.entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class NoteRepository {

    private final List<Note> notes = new ArrayList<>();


    public List<Note> listAll() {
        return notes;
    }

    public Note add(Note note) {
        notes.add(note);
        return note;
    }

    public boolean deleteById(long id) {
        return notes.remove(getById(id));
    }

    public boolean update(Note note) {
        AtomicBoolean occurs = new AtomicBoolean(false);
        notes.replaceAll((note1) -> {
            if (Objects.equals(note1.getId(), note.getId())) {
                occurs.set(true);
                return note;
            } else {
                return note1;
            }
        });

        return occurs.get();
    }

    public Note getById(long id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }

        return null;
    }
}
