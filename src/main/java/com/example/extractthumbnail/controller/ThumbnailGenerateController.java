package com.example.extractthumbnail.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.extractthumbnail.service.ThumbnailGenerateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("extract/thumbnail")
public class ThumbnailGenerateController {

    private final ThumbnailGenerateService service;

    @PostMapping(path = "")
    public Map<String, Object> extractThumbnail() {

        service.generate();

        return Collections.singletonMap("result", true);
    }

}
