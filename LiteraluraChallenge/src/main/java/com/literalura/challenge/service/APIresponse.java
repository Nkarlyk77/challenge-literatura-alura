package com.literalura.challenge.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.literalura.challenge.model.BookData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record APIresponse(
        int count,
        List<BookData> results
) {


}

