package com.noirix.controller.requests;

import lombok.Data;

@Data
public class SearchCriteria {
    private String query;

    private Long limit;

    private Long userLowerId;

    private Long offset;
}
