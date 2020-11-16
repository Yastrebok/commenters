package com.fill.commenter.controller;

import com.fill.commenter.mq.Consumer;
import com.fill.commenter.mq.MyProducer;
import com.fill.commenter.repository.CommentRepository;
import com.fill.commenter.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ControllerTest {
    private static int totalComments = 1000;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void addComments() throws Exception {

        String commentBody = "";

        for (int i = 1; i <= totalComments; i++) {
            commentBody = commentBody + i;
            this.mockMvc.perform(post("/add")
                    .param("commentBody", commentBody))
                    .andExpect(status().isOk());
        }
        Thread.sleep(5000 * totalComments);

        getStatistic();
        commentRepository.deleteAll();
        noticeRepository.deleteAll();
    }


    public void getStatistic() {
        int countSavedComments = commentRepository.findAll().size();
        int countSavedNotices = noticeRepository.findAllByDelivered(true).size();

        log.info("Saved Comments    = " + (countSavedComments * 100 / totalComments) + "%");
        log.info("Delivered Notices = " + (countSavedNotices * 100 / totalComments) + "%");
    }
}