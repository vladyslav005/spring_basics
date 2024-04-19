package idk.service;

import idk.entity.Note;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private NoteRepository noteRepository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            add(new Note(null, "TITLE " + i, "content " + i));
        }

        deleteById(noteRepository.listAll().get(0).getId());
        System.out.println(noteRepository.listAll());
    }


    @Override
    public List<Note> listAll() {
        return noteRepository.listAll();
    }

    @Override
    public Note add(Note note) {
        note.setId(Math.abs(secureRandom.nextLong()));
        return noteRepository.add(note);
    }

    @Override
    public void deleteById(long id) {
        if (!noteRepository.deleteById(id)) {
            throw new RuntimeException("Note not found");
        }
    }

    @Override
    public void update(Note note) {
        if (!noteRepository.update(note)) {
            throw new RuntimeException("Note not found");
        }
    }

    @Override
    public Note getById(long id) {
        Note note = noteRepository.getById(id);
        if (note == null) {
            throw new RuntimeException("Note not found");
        }

        return note;
    }
}
