package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Note;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByPlantId(int plantId);
}
