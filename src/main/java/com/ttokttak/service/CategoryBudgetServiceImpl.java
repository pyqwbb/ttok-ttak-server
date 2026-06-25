package com.ttokttak.service;

import com.ttokttak.dao.CategoryBudgetDao;
import com.ttokttak.domain.CategoryBudget;
import com.ttokttak.dto.CategoryBudgetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryBudgetServiceImpl implements CategoryBudgetService {

    private final CategoryBudgetDao categoryBudgetDao;

    @Override
    public List<CategoryBudget> getAll(String uid) {
        return categoryBudgetDao.findByUid(uid);
    }

    @Override
    public void create(String uid, CategoryBudgetRequest req) {
        CategoryBudget cb = new CategoryBudget();
        cb.setId(UUID.randomUUID().toString().substring(0, 8));
        cb.setUid(uid);
        cb.setCid(req.getCid());
        cb.setAmount(req.getAmount());
        categoryBudgetDao.insert(cb);
    }

    @Override
    public void update(String id, CategoryBudgetRequest req) {
        CategoryBudget cb = new CategoryBudget();
        cb.setId(id);
        cb.setAmount(req.getAmount());
        categoryBudgetDao.update(cb);
    }

    @Override
    public void delete(String id) {
        categoryBudgetDao.delete(id);
    }
}
