package com.tiki.member.domain.paging;

import lombok.Data;

@Data
public class SearchDTO {
    private int start;
    private int end;
    private int currentPage;
}


