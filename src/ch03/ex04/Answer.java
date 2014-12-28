/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex04;

/**
 *
 * How many functional interfaces with Filter in their name can you find in the
 * Java API? Which ones add value over Predicate<T>?
 *
 * 名前の中にFilterを含む関数型インタフェースが、Java API にはいくつありますか。
 * そのうちのどれが、Predicate<T>よりも付加価値がありますか。
 */
public class Answer {

    /*
     * java.io.FileFilter
     * java.io.FilenameFilter
     * java.util.logging.Filter
     * javax.imageio.spi.ServiceRegistry
     * javax.management.NotificationFilter
     * javax.xml.stream.EventFilter
     * javax.xml.stream.StreamFilter
     * org.xml.sax.XMLFilter
     *
     * Only FilenameFilter's method has two arguments instead of one.
     */
}
