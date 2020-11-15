package com.fill.commenter.service;

import com.fill.commenter.entiry.Comment;
import com.fill.commenter.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NoticeService noticeService;

    public Page<Comment> getAllComment(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Comment addComment(String commentBody) {
        Comment comment = new Comment();
        comment.setComment(commentBody);
        Optional<Comment> savedComment = Optional.of(commentRepository.save(comment));
        try {
            BusinessLogic.doSomeWorkOnCommentCreation();
            noticeService.add(savedComment.get().getId());
            return savedComment.get();
        } catch (Exception e) {
            commentRepository.delete(savedComment.get());
            comment.setComment("Some error, comment must be deleted");
            return comment;
        }
    }


}
