package com.reviewPointMangeServer.domain.result;

import lombok.Data;

import java.util.List;

@Data
public class MultipleResult <T> extends Result {
    private List<T> data;
}
