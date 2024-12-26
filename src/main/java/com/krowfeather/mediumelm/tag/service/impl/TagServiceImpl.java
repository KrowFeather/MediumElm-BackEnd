package com.krowfeather.mediumelm.tag.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.tag.entity.Tag;
import com.krowfeather.mediumelm.tag.mapper.TagMapper;
import com.krowfeather.mediumelm.tag.service.TagService;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    private final TagMapper tagMapper;
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }
}
