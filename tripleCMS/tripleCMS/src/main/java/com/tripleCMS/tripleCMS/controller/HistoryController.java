package com.tripleCMS.tripleCMS.controller;

import com.tripleCMS.tripleCMS.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/history")
public class HistoryController {

    private final HistoryService historyService;



}
