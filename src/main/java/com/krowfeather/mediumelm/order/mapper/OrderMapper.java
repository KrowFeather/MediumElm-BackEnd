package com.krowfeather.mediumelm.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.krowfeather.mediumelm.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Update("update orders set status = #{status} where id = #{oid}")
    void updateByOid(String oid, Integer status);
}
