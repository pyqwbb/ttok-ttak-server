package com.ttokttak.service;

import com.ttokttak.dao.ReactionMessageDao;
import com.ttokttak.domain.ReactionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactionMessageServiceImpl implements ReactionMessageService {

    private final ReactionMessageDao reactionMessageDao;

    @Override
    public List<ReactionMessage> getAll() {
        return reactionMessageDao.findAll();
    }
}
