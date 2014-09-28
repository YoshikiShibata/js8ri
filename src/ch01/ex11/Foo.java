/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex11;

/**
 * Suppose you have a class that implements two interfaces I and J, each of
 * which has a method void f(). Exactly what happens if f is an abstract,
 * default, or static method of I and an abstract, default, or static method of
 * J? Repeat where a class extends a superclass S and implements an interface I,
 * each of which has a method void f().
 *
 * void f() メソッドを持つ、I とJ の2 つのインタフェースがあり、両方を実装してい るクラスがあるとします。I インタフェースのf
 * メソッドが抽象、デフォルト、static の どれかであり、J インタフェースのf メソッドが抽象、デフォルト、static のどれかであ
 * る場合、すべての組み合わせで何が起きるでしょうか。同じように、スーパークラスS を 拡張し、I
 * インタフェースを実装した場合に、スーパークラスもインタフェースもvoid f() メソッドを持っていたら、どうなるかを調べなさい。
 *
 * ANSWER
 *
 * <pre>
 *             | J abstract       | J default         | J static
 * I abstract  | need to override | need to override  | need to override
 * I default   | need to override | need to override  | nothing to do
 * I static    | need to override | nothing to do     | nothing to do
 * </pre>
 *
 */
public class Foo implements I, J {

}
