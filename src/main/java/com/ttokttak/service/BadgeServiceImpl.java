package com.ttokttak.service;

import com.ttokttak.dao.BadgeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeDao badgeDao;


    @Override
    public List<Map<String, Object>> getMonthlyBadges(String uid) {
        return badgeDao.findMonthlyTopCategory(uid);
    }
}
