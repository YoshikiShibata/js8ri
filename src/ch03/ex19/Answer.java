/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex19;

/**
 * Look at the Stream<T> method <U> U reduce(U identity,
 * BiFunction<U,? super T,U>
 * accumulator, BinaryOperator<U> combiner). Should U be declared as ? super U
 * in the first type argument to BiFunction? Why or why not?
 *
 * Stream<T>のメソッドである<U> U reduce(U identity, BiFunction<U, ?
 * super T, U> accumulator, BinaryOperator<U> combiner) を見てみなさ い。BiFunction
 * への最初の型引数でU を? super U と宣言すべきですか。その理由 は、何ですか。
 */
public class Answer {
    // U is also used as the return value, so it should not be declared as ? super u,
    // mainly becuase it doesn't bring any benefit.
}
