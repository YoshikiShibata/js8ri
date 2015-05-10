/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch09.ex05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * ファイルからすべての文字を読み込み、逆順に書き出すプログラムを作成しなさい。 Files.readAllBytes とFiles.write
 * を使用しなさい。
 */
public class ReverseContents {
    
    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("alice.txt"));
        bytes = reverse(bytes);
        Files.write(Paths.get("/tmp/alice.txt"), bytes, StandardOpenOption.CREATE_NEW);
    }
    
    private static byte[] reverse(byte[] bytes) {
        int length = bytes.length;
        byte[] result = new byte[length];
        
        
        for (int i = 0; i < length; i++)
            result[i] = bytes[length - 1 - i];
        
        return result;
    }

}
