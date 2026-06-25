package com.ttokttak.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryBudget {
    private String id;
    private String uid;
    private int cid;
    private int amount;
}
