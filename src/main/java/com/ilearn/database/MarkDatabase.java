package com.ilearn.database;

import com.ilearn.domain.Mark;
import com.ilearn.repository.MarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkDatabase {
    private MarkRepository markRepository;

    public MarkDatabase(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    public Mark getMark(Long id) {
        return markRepository.findById(id).orElse(new Mark());
    }

    public Mark saveMark(final Mark mark) {
        return markRepository.save(mark);
    }

    public void deleteMark(Long id) {
        markRepository.deleteById(id);
    }

    public List<Mark> findAllByStudentId(Long id) {
        return markRepository.findAllByStudentId(id);
    }

}
