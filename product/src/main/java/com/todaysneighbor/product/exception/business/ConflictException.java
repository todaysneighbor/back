package com.todaysneighbor.product.exception.business;

import com.todaysneighbor.product.exception.ErrorCode;
import com.todaysneighbor.product.exception.business.BusinessException;

public class ConflictException extends BusinessException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}

