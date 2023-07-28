package ru.netology.graphics.image;

public class TextColor implements TextColorSchema {
    char[] c = new char[]{'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {

        return c[(int) Math.floor(color / 256.0 * c.length)];
    }

}
