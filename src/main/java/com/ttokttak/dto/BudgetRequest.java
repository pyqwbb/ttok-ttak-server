package com.ttokttak.dto;

import lombok.Data;

@Data
public class BudgetRequest {
    private String type;
    private String date;
    private String title;
    private int amount;
    private int cid;
    private String memo;
}
