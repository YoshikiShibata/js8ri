/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch09.ex07;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * ウェブページの内容を読み込んで、ファイルに保存するプログラムを作成しなさい。 URL.openStream とFiles.copy を使用しなさい。
 */
public class FetchURL {

    public static void main(String[] args) throws MalformedURLException, IOException {
        if (args.length != 2) {
            showUsage();
            System.exit(1);
        }

        try (InputStream is = new URL(args[0]).openStream()) {
            Files.copy(is, Paths.get(args[1]), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static void showUsage() {
        System.out.println("Usage: FetchURL URL file");
    }
}
