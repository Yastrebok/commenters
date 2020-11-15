package com.fill.commenter.controller;


import com.fill.commenter.entiry.Comment;
import com.fill.commenter.entiry.Notice;
import com.fill.commenter.service.CommentService;
import com.fill.commenter.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


@RestController
public class Controller {

    @Autowired
    private CommentService commentService;
    @Autowired
    private NoticeService noticeService;

    @PostMapping("/add")
    public String addComments(@RequestParam String commentBody) {
        return commentService.addComment(commentBody).toString();
    }

    @GetMapping("/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Page<Comment> getListComments(@PageableDefault Pageable pageable) {
        return commentService.getAllComment(pageable);
    }

    @GetMapping("/notices")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllNotices() {
        return noticeService.getAllNotices().toString();


    }

    @GetMapping("/comments2")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Notice> getListComments() {

        return noticeService.getAllNotices();


    }
}
