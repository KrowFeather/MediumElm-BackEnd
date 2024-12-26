package com.krowfeather.mediumelm.merchant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krowfeather.mediumelm.merchant.enitity.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {
    @Select("select m.* from merchant m, tag t where m.id = t.vid and t.cid = #{tagId}")
    List<Merchant> getMerchantsByTag(Integer tagId);
}
