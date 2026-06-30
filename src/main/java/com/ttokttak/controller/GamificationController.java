package com.ttokttak.controller;

import com.ttokttak.domain.MonthlySummaryMessage;
import com.ttokttak.domain.ReactionMessage;
import com.ttokttak.service.BadgeService;
import com.ttokttak.service.MonthlySummaryMessageService;
import com.ttokttak.service.ReactionMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GamificationController {

    private final ReactionMessageService reactionMessageService;
    private final MonthlySummaryMessageService monthlySummaryMessageService;
    private final BadgeService badgeService;

    // GET /api/reaction-messages
    @GetMapping("/reaction-messages")
    public ResponseEntity<List<ReactionMessage>> getReactionMessages() {
        return ResponseEntity.ok(reactionMessageService.getAll());
    }

    // GET /api/monthly-summary-messages
    @GetMapping("/monthly-summary-messages")
    public ResponseEntity<List<MonthlySummaryMessage>> getMonthlySummaryMessages() {
        return ResponseEntity.ok(monthlySummaryMessageService.getAll());
    }

    // GET /api/badges
    @GetMapping("/badges")
    public ResponseEntity<List<Map<String, Object>>> getBadges(HttpServletRequest request) {
        String uid = (String) request.getAttribute("uid");
        return ResponseEntity.ok(badgeService.getMonthlyBadges(uid));
    }
}
