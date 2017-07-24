package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.business.domain.T_Period;
import java.util.List;

public interface PeriodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(T_Period record);

    T_Period selectByPrimaryKey(Long id);

    List<T_Period> selectAll();

    int updateByPrimaryKey(T_Period record);
}