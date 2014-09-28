/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex12;

import java.util.ArrayList;
import java.util.List;

/**
 * In the past, you were told that it’s bad form to add methods to an interface
 * because it would break existing code. Now you are told that it’s okay to add
 * new methods, provided you also supply a default implementation. How safe is
 * that? Describe a scenario where the new stream method of the Collection
 * interface causes legacy code to fail compilation. What about binary
 * compatibility? Will legacy code from a JAR file still run?
 *
 * インタフェースにメソッドを追加することは既存のコードを動作させなくするので、過去
 * には、悪いことであると言われていました。今では、デフォルト実装も提供するのであれ
 * ば、新たなメソッドを追加することは問題ありません。そのような追加は、どれだけ安全
 * なのでしょうか。Collectionインタフェースの新たなstreamメソッドが古いコー ドの
 * コンパイルを失敗させるシナリオを述べなさい。バイナリ互換性についてはどうで
 * しょうか。JARファイルからの古いコードは、動作するでしょうか。
 */
public class LegacyOwnCollection extends ArrayList {

    /**
     * This stream() method could be compiled with Java 7 or older versions.
     * But with Java 8, this method is invalid because its return type is 
     * different from that of stream() method of Collection.
     * 
     * We lost source compatibility, but we keep binary compatibility, because
     * JVM allows a class to have two methods whose difference is only return 
     * type. 
     *
     * The legacy code which depends on this stream() method still works, 
     * because which method would be selected at runtime was already determined
     * at compile time: the signature and return type of a method determine the
     * selection.
     */
    public List<Integer> stream() { }
}
