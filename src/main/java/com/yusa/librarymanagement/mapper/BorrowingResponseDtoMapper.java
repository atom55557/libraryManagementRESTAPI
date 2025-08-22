package com.yusa.librarymanagement.mapper;

import com.yusa.librarymanagement.dto.BorrowingResponseDto;
import com.yusa.librarymanagement.model.Borrowing;

import java.util.List;
import java.util.stream.Collectors;

public class BorrowingResponseDtoMapper {

    public static BorrowingResponseDto toDto(Borrowing borrowing){
        BorrowingResponseDto dto = new BorrowingResponseDto();
        dto.setId(borrowing.getId());
        dto.setUserName(borrowing.getUser().getUserName());
        dto.setBookName(borrowing.getBook().getBookName());
        dto.setStatus(borrowing.getStatus());
        return dto;
    }

    public static List<BorrowingResponseDto> toDtoList(List<Borrowing> borrowings){
        return borrowings.stream().map(BorrowingResponseDtoMapper::toDto).collect(Collectors.toList());
    }
}
