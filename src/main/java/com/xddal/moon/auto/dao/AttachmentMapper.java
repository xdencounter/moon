package com.xddal.moon.auto.dao;

import com.xddal.moon.auto.entity.Attachment;
import java.util.List;

public interface AttachmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attachment record);

    Attachment selectByPrimaryKey(Long id);

    List<Attachment> selectAll();

    int updateByPrimaryKey(Attachment record);
}