package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class SizePictures implements TextGraphicsConverter {
    private int height;
    private int width;
    private double maxRatio;
    private double variation = 1.0;

    TextColorSchema schema;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int newWidth = img.getWidth();
        int newHeight = img.getHeight();
        double ratio;


        if (newWidth < newHeight) {
            ratio = (double) newHeight / newWidth;
        } else {
            ratio = (double) newWidth / newHeight;
        }
        if (ratio > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }

        if (img.getHeight() >= img.getWidth()) {
            if (img.getHeight() >= height) {
                variation = (double) img.getHeight() / height;
            }
        } else {
            if (img.getWidth() >= width) {
                variation = (double) img.getWidth() / width;
            }
        }
        newWidth = (int) (newWidth / variation);
        newHeight = (int) (newHeight / variation);
        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        // ImageIO.write(imageObject, "png", new File("out.png"));
        WritableRaster bwRaster = bwImg.getRaster();

        //int w = 0;
        //int h = 0;
        StringBuilder stb = new StringBuilder();
        for (int h = 0; h < bwRaster.getHeight(); h++) {
            for (int w = 0; w < bwRaster.getWidth(); w++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                stb.append(c).append(c);

            }
            stb.append("\n");
        }
        return stb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}





