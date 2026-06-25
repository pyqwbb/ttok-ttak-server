package com.ttokttak.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {
    private String id;
    private String uid;
    private int cid;
    private String title;
    private String date;
    private String type;
    private int amount;
    private String memo;
}
