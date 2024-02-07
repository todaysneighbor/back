package com.todaysneighbor.product.exception.business;

import com.todaysneighbor.product.exception.ErrorCode;
import com.todaysneighbor.product.exception.business.BusinessException;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
