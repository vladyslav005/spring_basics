package idk.service.notes;

import idk.entity.Note;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Slf4j
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final SecureRandom secureRandom = new SecureRandom();

    @Autowired
    private NoteRepository noteRepository;


    @Override
    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    @Override
    public Note add(Note note) {
        note.setId(Math.abs(secureRandom.nextLong()));
        return noteRepository.save(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note) {
        Note noteFromBd = noteRepository.findById(note.getId()).orElseThrow();
        noteFromBd.setTitle(note.getTitle());
        noteFromBd.setContent(note.getContent());
        noteRepository.save(noteFromBd);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.findById(id);
    }
}
