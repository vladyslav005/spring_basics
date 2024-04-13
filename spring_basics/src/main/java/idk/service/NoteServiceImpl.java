package idk.service;

import idk.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> listAll() {
        return noteRepository.listAll();
    }

    @Override
    public Note add(Note note) {
        return noteRepository.add(note);
    }

    @Override
    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void update(Note note) {
        noteRepository.update(note);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.getById(id);
    }
}
