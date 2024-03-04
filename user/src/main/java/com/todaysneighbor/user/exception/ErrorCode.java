package com.todaysneighbor.user.exception;

public enum ErrorCode {

    ENTITY_NOT_FOUND(700, "000", "대상을 찾을 수 없습니다."),
    DELETED_DATA(700,"000","삭제된 데이터 입니다"),
    SOLD_PRODUCT(700,"000","판매된 데이터 입니다"),
    INVALID_INPUT_VALUE(700, "000", "데이터 입력을 확인해주세요."),
    CATEGORY_NOT_FOUND(700, "000", "해당 카테고리를 찾을 수 없습니다."),
    AUTH_TIME_OUT(701, "001", "토큰이 만료 되었습니다."),
    AUTH_INVALID(702, "002", "토큰이 변조 되었습니다."),
    NO_AUTH(703, "003", "권한이 없습니다."),
    INTERNAL_SERVER_ERROR(705, "005", "서버 내부적으로 문제가 있습니다."),
    FILE_INVALID(706, "006", "파일명에 부적합 문자가 포함되어 있습니다."),
    FILE_UPLOAD_FAIL(707, "007", "파일 업로드에 실패했습니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
