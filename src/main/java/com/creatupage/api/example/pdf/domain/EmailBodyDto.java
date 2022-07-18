package com.creatupage.api.example.pdf.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailBodyDto {

    private String email;
    private String content;
    private String subject;

}
