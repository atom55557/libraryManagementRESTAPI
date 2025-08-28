package com.yusa.librarymanagement.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BorrowingUserRequestDto {
    private String bookName;
}
