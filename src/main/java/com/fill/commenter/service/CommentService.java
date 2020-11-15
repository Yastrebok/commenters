package com.fill.commenter.service;

import com.fill.commenter.entiry.Comment;
import com.fill.commenter.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        Comment savedComment = commentRepository.save(comment);
        if (!savedComment.equals(null)){
            try {
                BusinessLogic.doSomeWorkOnCommentCreation();
                noticeService.add(savedComment.getId());
            } catch (Exception e) {
                commentRepository.delete(savedComment);
            }
        }
        return savedComment;
    }


}
