/*
 * Copyright (C) 2014, 2016 Yoshiki Shibata. All rights reserved.
 */

/**
 * Is the comparator code in the Arrays.sort method called in the same thread as
 * the call to sort or a different thread?
 * 
 * Arrays.sort メソッド内で呼び出されるコンパレータのコードは、sort メソッドを
 * 呼び出したスレッドで実行されるでしょうか。それとも、別のスレッドで実行されるで
 * しょうか。
 */
public class Answer {
    // The Comparator is invoked inside the thread calling Arrays.sort.
}
