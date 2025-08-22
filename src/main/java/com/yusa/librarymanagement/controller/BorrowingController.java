package com.yusa.librarymanagement.controller;

import com.yusa.librarymanagement.dto.BorrowingRequestDto;
import com.yusa.librarymanagement.dto.BorrowingResponseDto;
import com.yusa.librarymanagement.mapper.BorrowingResponseDtoMapper;
import com.yusa.librarymanagement.service.BorrowingService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService borrowingService;

    @GetMapping
    public List<BorrowingResponseDto> getBorrowingList(){
        return BorrowingResponseDtoMapper.toDtoList(borrowingService.getAllBorrowing());
    }

    @GetMapping("/{userId}")
    public List<BorrowingResponseDto> getUserBorrowingList(@PathVariable long userId){
        return BorrowingResponseDtoMapper.toDtoList(borrowingService.getBorrowingByUserId(userId));
    }

    @PostMapping
    public BorrowingResponseDto createBorrowing(@RequestBody BorrowingRequestDto borrowingRequestDto){
        return BorrowingResponseDtoMapper.toDto(borrowingService.create(borrowingRequestDto));
    }

    @PutMapping
    public BorrowingResponseDto updateBorrowing(@RequestParam long id){
        return BorrowingResponseDtoMapper.toDto(borrowingService.returnBorrowing(id));
    }


}
