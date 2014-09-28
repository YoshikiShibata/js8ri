/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex06;

/*
 * Didn’t you always hate it that you had to deal with checked exceptions in a
 * Runnable? Write a method uncheck that catches all checked exceptions and turns
 * them into unchecked exceptions. For example,
 * 
 * Runnable 内でチェックされる例外を処理しなければならないことが、いつも面倒だと
 * 思っていませんか。チェックされるすべての例外をキャッチし、それをチェックされない
 * 例外へ変える uncheck メソッドを書きなさい。たとえば、次のように使用します。
 * 
 * <blockquote><pre>
 * new Thread(uncheck(() -> 
 *    { System.out.println("Zzz"); Thread.sleep(1000); })).
 *    start();
 *       // catch (InterruptedException)が必要ありません!
 * </pre></blockquote>
 *
 * Hint: Define an interface RunnableEx whose run method may throw any exceptions.
 * Then implement public static Runnable uncheck(RunnableEx runner). Use a
 * lambda expression inside the uncheck function.
 *
 * ヒント:どのような例外でもスローできる run メソッドを持つ RunnableEx インタフ
 * ェースを定義します。そして、public static Runnable uncheck(RunnableEx runner)
 * を実装します。uncheck 関数内でラムダ式を使用します。
 *
 * Why can’t you just use ¥texttt{Callable<Void>} instead of RunnableEx?
 * 
 * なぜ、RunnableEx の代わりに Callable<Void>を使用できないのでしょうか。
 * [Answer: Callble<Void> is not compatible with Runnable as a functional 
 * interface, because its call() method has a return type.
 */
public class ExceptionUtils {

    private ExceptionUtils() {
    } // Non-instantiable

    /**
     * Converts any objects which implements Runnable to another Runnable
     * which don't throw any checked exception. 
     * 
     * @param runnable Runnable object to be run
     * @return Runnable object which never throw any checked exception
     */
    public static Runnable uncheck(RunnableEx runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

}
