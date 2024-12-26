package com.krowfeather.mediumelm.merchandise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krowfeather.mediumelm.merchandise.entity.Merchandise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MerchandiseMapper extends BaseMapper<Merchandise> {
    @Select("select * from merchandise where menu_id=#{id}")
    List<Merchandise> getMerchandisesByMid(String id);
}
