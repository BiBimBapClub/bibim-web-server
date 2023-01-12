package com.bibimbap.bibimweb.util.exception;

public class ConflictException extends RuntimeException {
    public static final ConflictException MEMBER = new ConflictException("중복 회원입니다");
    public static final ConflictException ARCHIVE = new ConflictException("중복 파일 이름 입니다");
    public static final ConflictException COMPETITION = new ConflictException("중복 대회 이름 입니다");

    private ConflictException(String message) {
        super(message);
    }

}
