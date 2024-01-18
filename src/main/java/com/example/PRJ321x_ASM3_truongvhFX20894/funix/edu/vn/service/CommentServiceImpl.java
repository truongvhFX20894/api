package com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.service;

import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Clinics;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Comments;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.DoctorUsers;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.model.Specializations;
import com.example.PRJ321x_ASM3_truongvhFX20894.funix.edu.vn.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentsRepository commentsRepository;

    @Autowired
    public CommentServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<Comments> findAll() {
        return commentsRepository.findAll();
    }

    @Override
    public Comments findById(int id) {
        Optional<Comments> result = commentsRepository.findById(id);
        Comments comments = null;
        if (result.isPresent()) {
            comments = result.get();
        }
        return comments;
    }

    @Override
    public void save(Comments comments) {
        commentsRepository.save(comments);
    }

    @Override
    public void deleteById(int id) {
        commentsRepository.deleteById(id);
    }
}
