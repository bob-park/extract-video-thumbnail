package com.example.extractthumbnail.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.jcodec.api.JCodecException;
import org.springframework.stereotype.Service;

import com.example.extractthumbnail.common.ThumbnailGeneratorUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ThumbnailGenerateService {

    private static final String VIDEO = "/Users/hwpark/Documents/clip/video/video.mp4";
    private static final String BASE_THUMBNAIL_FOLDER = "/Users/hwpark/Documents/clip/thumbnail";

    private static final int THUMBNAIL_WIDTH = 600;

    public void generate() {

        String baseName = FilenameUtils.getBaseName(VIDEO);

        File videoFile = new File(VIDEO);

        File thumbnailDest = new File(BASE_THUMBNAIL_FOLDER + File.separatorChar + baseName + ".jpg");

        try {
            ThumbnailGeneratorUtils.extractThumbnail(videoFile, thumbnailDest, THUMBNAIL_WIDTH);
        } catch (IOException | JCodecException e) {
            throw new RuntimeException(e);
        }

    }

}
