package com.todaysneighbor.user.exception.business;

import com.todaysneighbor.user.exception.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
