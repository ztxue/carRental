package com.shengyi.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassOptions {
    private Long value;

    private String label;

    private List<CarOptions> children = new ArrayList<>();
}
