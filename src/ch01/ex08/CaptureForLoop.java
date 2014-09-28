/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex08;

import java.util.ArrayList;
import java.util.List;
/**
 * What happens when a lambda expression captures values in an enhanced for loop
 * such as this one?
 *
 * ラムダ式が次のような拡張¥texttt{for}ループ内の値をキャプチャした場合には、どうなりますか。
 *
 * <blockquote><pre>
 * String[] names = { "Peter", "Paul", "Mary" };
 * List<Runnable> runners = new ArrayList<>();
 * for (String name : names)
 *     runners.add(() -> System.out.println(name));
 * </blockquote></pre>
 *
 * Is it legal? Does each lambda expression capture a different value, or do
 * they all get the last value? What happens if you use a traditional loop for
 * <pre>(int i = 0; i < names.length; i++)</pre>?
 *
 * これは、正当なコードでしょうか。各ラムダ式は異なる値をキャプチャするのでしょう
 * か。それとも、すべてのラムダ式が最後の値をキャプチャするのでしょうか。従来の
 * <pre>for (int i = 0; i < names.length; i++)</pre>ループを使用すると、どうなるでしょうか。
 */
public class CaptureForLoop {

    public static void main(String[] args) {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();
        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }

        runners.forEach(Runnable::run);
        
        runners.clear();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            runners.add(() -> System.out.println(name));
            
            // Following code is invalid, because the loop variable i is not
            // effectively final.
            //
            // runners.add(() -> System.out.println(names[i]));
        }
        
        runners.forEach(Runnable::run);
    }

}
