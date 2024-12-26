package com.krowfeather.mediumelm.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krowfeather.mediumelm.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select * from menu where mid = #{id}")
    List<Menu> getMenuByMid(String id);
}
