/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * The characterStream method in Section 2.3, “The filter, map, and flatMap
 * Methods,” on page 25, was a bit clumsy, first filling an array list and then
 * turning it into a stream. Write a stream-based one-liner instead. One
 * approach is to make a stream of integers from 0 to s.length() - 1 and map
 * that with the s::charAt method reference.
 *
 * 31 ページの2.3 節「filter、map、flatMap メソッド」のcharacterStream メソッ ドは、最初にArrayList
 * を埋めて、それからそのリストをストリームに変換するとい う具合に、多少ぎこちないです。代わりに、ストリームを使用して、1 行で書きなさい。
 * 適切な方法は、0 からs.length() - 1 までの整数のストリームを作成して、それを s::charAt メソッド参照でマップすることです。
 */
public class CharacterUtil {

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add(c);
        }

        return result.stream();
    }

    public static Stream<Character> characterStream2(String s) {
        IntStream is = IntStream.range(0, s.length());

        return is.mapToObj(s::charAt);
    }
}
