package com.literalura.challenge.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
    String title,
    Long download_count,
    List<AuthorData> authors,
    List<String> languages
) {
}
