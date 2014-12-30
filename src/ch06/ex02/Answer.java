/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex02;

/**
 * Does a LongAdder help with yielding a sequence of increasing IDs? Why or why
 * not?
 *
 * 増加するID 列を生成するためにLongAdder は役立ちますか。その答えの理由を述べな さい。
 */
public class Answer {
    
    // To generate a sequnece of increasing IDs, two operations are required:
    // increment() and sum(). So additional lock is needed.

}
