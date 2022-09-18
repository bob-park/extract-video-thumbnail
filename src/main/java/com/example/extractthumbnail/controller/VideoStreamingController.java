package com.example.extractthumbnail.controller;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.extractthumbnail.service.VideoStreamingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("video/streaming")
public class VideoStreamingController {

    private final VideoStreamingService service;

    @GetMapping(path = "")
    public ResponseEntity<ResourceRegion> streaming(@RequestHeader HttpHeaders headers) {

        ResourceRegion videoStreaming = service.getVideoStreaming(headers);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .contentType(
                MediaTypeFactory.getMediaType(videoStreaming.getResource())
                    .orElse(MediaType.APPLICATION_OCTET_STREAM))
            .body(videoStreaming);

    }
}
