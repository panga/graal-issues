package com.github.panga;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {
        URL resource = ClassLoader.getSystemClassLoader().getResource("test.properties");
        InputStream stream = resource.openStream();

        System.out.println("stream.available()=" + stream.available());

        StringBuilder content = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                content.append((char) c);
            }
        }

        System.out.println("content.length()=" + content.length());

        stream.close();
        stream = resource.openStream();

        System.out.println("new stream.available()=" + stream.available());
    }

}
