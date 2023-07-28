package ru.netology.graphics;

import ru.netology.graphics.image.*;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        SizePictures converter = new SizePictures();
        //TextColor textColor = new TextColor();
        converter.setTextColorSchema(new TextColor());
        //TextColorSchema rt = new TextColor();

        // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
        //String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        //String imgTxt = converter.convert(url);
        //System.out.println(imgTxt);
    }
}

