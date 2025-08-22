package com.yusa.librarymanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BorrowingRequestDto {
    private Long userId;
    private Long bookId;

}
