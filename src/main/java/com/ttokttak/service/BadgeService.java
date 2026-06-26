package com.ttokttak.service;

import java.util.List;
import java.util.Map;

public interface BadgeService {
    List<Map<String, Object>> getMonthlyBadges(String uid);
}
