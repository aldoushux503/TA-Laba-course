package com.labas.store.dao.mybatis;

import com.labas.store.dao.IProductReviewDAO;
import com.labas.store.mapper.IProductReviewMapper;
import com.labas.store.model.entities.ProductReview;

public class MyBatisProductReviewDAO extends MyBatisAbstractDAO<ProductReview, Long> implements IProductReviewDAO {

    public MyBatisProductReviewDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IProductReviewMapper.class;
    }
}
