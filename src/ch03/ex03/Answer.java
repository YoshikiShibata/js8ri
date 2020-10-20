/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex03;

/**
 * Java 1.4 added assertions to the language, with an assert keyword. Why were
 * assertions not supplied as a library feature? Could they be implemented as a
 * library feature in Java 8?
 *
 * Java 1.4 は、予約語assert でもって、Java 言語にアサーションを追加しました。なぜ、
 * アサーションは、ライブラリの機能として提供されなかったのでしょう。Java 8 ではライ ブラリの機能として実装することができますか。
 */
public class Answer {
    /*
     * According to the JSR 41: A Simple Assertion Facility 
	 * (https://www.jcp.org/en/jsr/detail?id=41), following 
     * characteristics were intended to achieve:
     *
     * 1) The facility should be simple and intuitive; it should not change 
     *    the character of the language. The facility should be sufficiently 
     *    pleasant to use that its use becomes widespread.
     * 2) It should be possible to disable the facility at a reasonably fine 
     *    granularity, and to enable assertion checking in the field.
     * 3) The facility should impose negligible runtime cost when it is disabled.
     * 4) The facility should require no change to the class file format, and 
     *    little or (preferably) no change to the JVM.
     *
     * 2) and 3) are are very difficult to implement with a library. So, even
     * with Java 8, it is not possible to implement assert factiliy with a library.
     */
}
