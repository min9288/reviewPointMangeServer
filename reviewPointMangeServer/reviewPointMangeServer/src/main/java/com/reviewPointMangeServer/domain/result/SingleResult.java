package com.reviewPointMangeServer.domain.result;

import lombok.Data;

@Data
public class SingleResult<T> extends Result {
    private T data;
}
