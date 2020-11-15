package com.fill.commenter.controller;

import com.fill.commenter.repository.CommentRepository;
import com.fill.commenter.repository.NoticeRepository;
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
public class ControllerTest {
    private static int totalComments = 20;

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

            this.mockMvc.perform(post("/add").param("commentBody", commentBody))
//                    .andDo(print())
                    .andExpect(status().isOk());

        }


        getStatistic();
        commentRepository.deleteAll();
        noticeRepository.deleteAll();
    }


    public void getStatistic() {
        int countSavedComments = commentRepository.findAll().size();
        System.out.println("Saved Comments = " + countSavedComments);

        int countSavedNotices = noticeRepository.findAllByDelivered(true).size();
        System.out.println("Saved Notices = " + countSavedNotices);

        System.out.println("Saved Comments    = " + (countSavedComments * 100 / totalComments) + "%");
        System.out.println("Delivered Notices = " + (countSavedNotices * 100 / totalComments) + "%");

    }
}