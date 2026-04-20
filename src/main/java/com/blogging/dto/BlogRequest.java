package com.blogging.dto;

import com.blogging.entity.PostStatus;
import lombok.Data;

@Data
public class BlogRequest {
    private String title;
    private String content;
    private String tags;
    private PostStatus status;
}
