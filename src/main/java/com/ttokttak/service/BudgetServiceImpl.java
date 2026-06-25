package com.ttokttak.service;

import com.ttokttak.dao.BudgetDao;
import com.ttokttak.domain.Budget;
import com.ttokttak.dto.BudgetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetDao budgetDao;

    @Override
    public List<Budget> getAll(Map<String, Object> params) {
        return budgetDao.findAll(params);
    }

    @Override
    public Budget getById(String id) {
        return budgetDao.findById(id);
    }

    @Override
    public void create(String uid, BudgetRequest req) {
        Budget budget = new Budget();
        budget.setId(UUID.randomUUID().toString().substring(0, 8));
        budget.setUid(uid);
        budget.setCid(req.getCid());
        budget.setTitle(req.getTitle());
        budget.setDate(req.getDate());
        budget.setType(req.getType());
        budget.setAmount(req.getAmount());
        budget.setMemo(req.getMemo());
        budgetDao.insert(budget);
    }

    @Override
    public void update(String id, BudgetRequest req) {
        Budget budget = new Budget();
        budget.setId(id);
        budget.setCid(req.getCid());
        budget.setTitle(req.getTitle());
        budget.setDate(req.getDate());
        budget.setType(req.getType());
        budget.setAmount(req.getAmount());
        budget.setMemo(req.getMemo());
        budgetDao.update(budget);
    }

    @Override
    public void delete(String id) {
        budgetDao.delete(id);
    }
}
