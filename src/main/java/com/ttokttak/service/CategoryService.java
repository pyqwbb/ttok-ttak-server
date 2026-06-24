package com.ttokttak.service;

import com.ttokttak.domain.Category;

import java.util.List;

public interface CategoryService {
   List<Category> getAll();
   List<Category> getByType(String type);
}
