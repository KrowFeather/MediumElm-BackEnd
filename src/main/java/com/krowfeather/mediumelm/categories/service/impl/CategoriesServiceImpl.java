package com.krowfeather.mediumelm.categories.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.categories.entity.Categories;
import com.krowfeather.mediumelm.categories.mapper.CategoriesMapper;
import com.krowfeather.mediumelm.categories.service.CategoriesService;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper,Categories> implements CategoriesService {
}
