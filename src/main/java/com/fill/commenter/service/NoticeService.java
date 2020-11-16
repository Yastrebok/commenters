package com.fill.commenter.service;

import com.fill.commenter.entity.Notice;
import com.fill.commenter.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public Page<Notice> getAllNotices(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }

    public Notice add(Long commentId) {
        Notice newNotice = new Notice();
        newNotice.setComment_id(commentId);
        Optional<Notice> savedNotice;
        try {
            BusinessLogic.doSomeWorkOnNotification();
            newNotice.setDelivered(true);
            savedNotice = Optional.of(noticeRepository.save(newNotice));
        } catch (Exception e) {
            newNotice.setDelivered(false);
            savedNotice = Optional.of(noticeRepository.save(newNotice));
        }
        return savedNotice.get();
    }

}
