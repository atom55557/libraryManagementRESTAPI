package com.yusa.librarymanagement.dto;

import com.yusa.librarymanagement.enums.Status;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class BorrowingResponseDto {
    private long id;
    private String bookName;
    private String userName;
    private Status status;
}
