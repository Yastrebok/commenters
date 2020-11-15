package com.fill.commenter.service;

import com.fill.commenter.entiry.Notice;
import com.fill.commenter.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice add(Long commentId) {
        Notice newNotice = new Notice();
        newNotice.setComment_id(commentId);
        Optional<Notice> savedNotice = Optional.of(noticeRepository.save(newNotice));
        if (savedNotice.isPresent()) {
            try {
                BusinessLogic.doSomeWorkOnNotification();
                savedNotice.get().setDelivered(true);
                savedNotice = Optional.of(noticeRepository.save(newNotice));
            } catch (Exception e) {
                savedNotice.get().setDelivered(false);
                savedNotice = Optional.of(noticeRepository.save(newNotice));
            }
        }
        return savedNotice.get();
    }

}
