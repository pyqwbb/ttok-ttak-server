package com.ttokttak.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionMessage {
    private int id;
    private int cid;
    private int goalCount;
    private String message;
}
