package com.ttokttak.service;

import com.ttokttak.dao.CategoryDao;
import com.ttokttak.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> getByType(String type) {
        return categoryDao.findByType(type);
    }
}
