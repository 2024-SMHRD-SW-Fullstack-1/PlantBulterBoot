package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // 특정 식물의 모든 메모 가져오기
    @GetMapping("/{plantId}")
    public List<Note> getNotesByPlantId(@PathVariable int plantId) {
        return noteService.getNotesByPlantId(plantId);
    }

    // 메모 추가
    @PostMapping
    public List<Note> createNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return noteService.getNotesByPlantId(note.getPlantId()); // 추가된 메모를 포함한 전체 목록 반환
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable int id) {
        noteService.deleteNoteById(id);
    }
}
