package com.yusa.librarymanagement.controller;

import com.yusa.librarymanagement.dto.BorrowingRequestDto;
import com.yusa.librarymanagement.dto.BorrowingResponseDto;
import com.yusa.librarymanagement.dto.BorrowingUserRequestDto;
import com.yusa.librarymanagement.mapper.BorrowingResponseDtoMapper;
import com.yusa.librarymanagement.service.BorrowingService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<BorrowingResponseDto> getBorrowingList(){
        return BorrowingResponseDtoMapper.toDtoList(borrowingService.getAllBorrowing());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{userId}")
    public List<BorrowingResponseDto> getUserBorrowingList(@PathVariable long userId){
        return BorrowingResponseDtoMapper.toDtoList(borrowingService.getBorrowingByUserId(userId));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/my")
    public List<BorrowingResponseDto> getMyBorrowingList(){
        return BorrowingResponseDtoMapper.toDtoList(borrowingService.getMyBorrowings());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public BorrowingResponseDto createBorrowing(@RequestBody BorrowingRequestDto borrowingRequestDto){
        return BorrowingResponseDtoMapper.toDto(borrowingService.create(borrowingRequestDto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/user")
    public BorrowingResponseDto userCreateBorrowing(@RequestBody BorrowingUserRequestDto request){
        return BorrowingResponseDtoMapper.toDto(borrowingService.userCreateBorrowing(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public BorrowingResponseDto updateBorrowing(@RequestParam long id){
        return BorrowingResponseDtoMapper.toDto(borrowingService.returnBorrowing(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/return/{bookId}")
    public BorrowingResponseDto returnBorrowing(@PathVariable long bookId){
        return BorrowingResponseDtoMapper.toDto(borrowingService.returnBorrowingUser(bookId));
    }


}
