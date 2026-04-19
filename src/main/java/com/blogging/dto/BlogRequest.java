package com.blogging.dto;

import lombok.Data;

@Data
public class BlogRequest {
    private String title;
    private String content;
    private String status;
}
