package com.example.extractthumbnail.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

import net.coobird.thumbnailator.Thumbnails;

public interface ThumbnailGeneratorUtils {

    static void extractThumbnail(File videoFile, File thumbnailDest, int thumbnailWidth) throws
        IOException,
        JCodecException {
        FrameGrab grab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(videoFile));

        grab.seekToSecondPrecise(0);

        Picture picture = grab.getNativeFrame();

        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);

        ImageIO.write(
            Thumbnails.of(bufferedImage)
                .width(thumbnailWidth)
                .asBufferedImage(),
            "jpg",
            thumbnailDest);
    }

}
