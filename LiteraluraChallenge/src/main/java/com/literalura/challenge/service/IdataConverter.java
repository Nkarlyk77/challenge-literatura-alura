package com.literalura.challenge.service;

public interface IdataConverter {
    <T> T getData(String json, Class<T> clase);
}
