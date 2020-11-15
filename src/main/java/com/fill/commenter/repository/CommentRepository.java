package com.fill.commenter.repository;

import com.fill.commenter.entiry.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    Page<Comment> findAll(Pageable pageable);

//        List<Comment> ();

//    long addComment(Comment comment);
}
