/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex02;

import java.util.List;
import js8ri.util.FileUtil;

/**
 * Verify that asking for the first five long words does not call the filter
 * method once the fifth long word has been found. Simply log each method call.
 *
 * ある文字数以上の長い単語のうち最初の5 個だけ求める処理において、5 番目の長い単語 がいったん見つかったら、filter
 * メソッドが呼び出されないことを検証しなさい。単純 に、各メソッドの呼び出しでログを出せばよいです。
 */
public class VerifyFilter {

    public static void main(String[] args) throws Exception {
        List<String> words = FileUtil.readAsWords("alice.txt");

        long count = words.stream().filter(w -> w.length() > 12).
                peek(w -> System.out.println("peeked")).limit(5).count();
    }

}
