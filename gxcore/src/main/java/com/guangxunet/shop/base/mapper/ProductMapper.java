package com.guangxunet.shop.base.mapper;

import com.guangxunet.shop.business.domain.Product;
import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);
}