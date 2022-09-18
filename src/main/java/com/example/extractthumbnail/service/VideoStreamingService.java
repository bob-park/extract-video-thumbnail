package com.example.extractthumbnail.service;

import java.io.IOException;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VideoStreamingService {

    private static final long CHUNK_SIZE = 2_097_152;

    private static final String VIDEO = "/Users/hwpark/Documents/clip/video/video.mp4";

    public ResourceRegion getVideoStreaming(HttpHeaders headers) {

        Resource resource = new FileSystemResource(VIDEO);

        HttpRange httpRange =
            headers.getRange().stream()
                .findFirst()
                .orElse(HttpRange.createByteRange(0));

        long contentLength;

        try {
            contentLength = resource.contentLength();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long start = httpRange.getRangeStart(contentLength);
        long end = httpRange.getRangeEnd(contentLength);

        long rangeLength = NumberUtils.min(CHUNK_SIZE, (end - start + 1));

        return new ResourceRegion(resource, start, rangeLength);

    }
}
