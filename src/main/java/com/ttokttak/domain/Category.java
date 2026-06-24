package com.ttokttak.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    private int id;
    private String name;
    private String img;
    private String color;
    private String type;  // 'income' | 'expense'
}
