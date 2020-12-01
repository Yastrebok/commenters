package com.fill.commenter.controller;


import com.fill.commenter.entity.Comment;
import com.fill.commenter.entity.Notice;
import com.fill.commenter.mq.MyProducer;
import com.fill.commenter.service.CommentService;
import com.fill.commenter.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@RestController
public class Controller {

    @Autowired
    private MyProducer producer;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NoticeService noticeService;

    @GetMapping("/hello")
    public String index() {
        return "Maybe its work";
    }


    @PostMapping("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addComments(@RequestParam String commentBody) {
        producer.produce(commentBody);
        return "Comment was sent - " + commentBody;
    }

    @GetMapping("/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Page<Comment> getPageOfComments(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.getAllComment(pageable);
    }

    @GetMapping("/notices")
    @Produces(MediaType.APPLICATION_JSON)
    public Page<Notice> getPageOfNotices(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return noticeService.getAllNotices(pageable);
    }

}
