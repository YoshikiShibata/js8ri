/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex11;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write a program that gets the contents of a password-protected web page. Call
 * URLConnection connection = url.openConnection();. Form the string username:
 * password and encode it in Base64. Then call connection.setRequestProperty(
 * "Authorization", "Basic " + encoded string), followed by connection.connect()
 * and connection.getInputStream().
 *
 * パスワード保護されたウェブページの内容を取得するプログラムを書きなさい。 URLConnection connection =
 * url.openConnection(); を呼び出しなさ い。文字列username:password を生成して、それをBase64 でエンコードしな
 * さい。それから、connection.setRequestProperty("Authorization", "Basic " + encoded
 * string) を呼び出し、次にconnection.connect() と connection.getInputStream() を呼び出しなさい。
 */
public class ShowContents {
    public static void main(String[] args) {
        if (args.length != 1) 
            showUsageAndExit();
        
        URL url = null;
        try {
            url = new URL(args[0]);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ShowContents.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Malformed URL: %s%n", args[0]);
        }
        String userName = System.console().readLine("User Name: ");
        char[] password = System.console().readPassword("Password: ");
        
        showContentsOfURL(url, userName, password);
    }
    
    private static void showUsageAndExit() {
        System.out.println("Usage: ShowContents URL");
        System.out.println();
        System.out.println("*** Sample Site ***");
        System.out.println("http://www.chama.ne.jp/htaccess_sample/index.htm");
        System.out.println("UserName: chama");
        System.out.println("Password: 1111");
        System.exit(1);
    }
    
    private static void showContentsOfURL(URL url, String userName, char[] password) {
        URLConnection connection;
        
        try {
            connection = url.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(ShowContents.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        Base64.Encoder encoder = Base64.getEncoder();
        String userNameAndPassword = userName + ":" + String.valueOf(password);
        String encodedString = encoder.encodeToString(
                userNameAndPassword.getBytes(StandardCharsets.UTF_8));
        
        connection.setRequestProperty("Authorization", "Basic " + encodedString);
        
        InputStream contents;
        try {
            contents = connection.getInputStream();
        } catch (IOException ex) {
            Logger.getLogger(ShowContents.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        try (Scanner scanner = new Scanner(contents)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
        
    }

}
