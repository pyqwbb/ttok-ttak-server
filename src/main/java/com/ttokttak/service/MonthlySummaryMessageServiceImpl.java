package com.ttokttak.service;

import com.ttokttak.dao.MonthlySummaryMessageDao;
import com.ttokttak.domain.MonthlySummaryMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlySummaryMessageServiceImpl implements MonthlySummaryMessageService {

    private final MonthlySummaryMessageDao monthlySummaryMessageDao;

    @Override
    public List<MonthlySummaryMessage> getAll() {
        return monthlySummaryMessageDao.findAll();
    }
}
