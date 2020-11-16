package com.fill.commenter.repository;

import com.fill.commenter.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Override
    Page<Notice> findAll(Pageable pageable);

    List<Notice> findAllByDelivered(boolean delivered);
}
