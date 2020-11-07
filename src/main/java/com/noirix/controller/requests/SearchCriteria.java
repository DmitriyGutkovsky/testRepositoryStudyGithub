package com.noirix.controller.requests;

import lombok.Data;

@Data
public class SearchCriteria {
    private String query;

    private Long limit;
}
