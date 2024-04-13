package idk.service;

import idk.entity.Note;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class NoteServiceImpl implements NoteService {

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private NoteRepository noteRepository;


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
