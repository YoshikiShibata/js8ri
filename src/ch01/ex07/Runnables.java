/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch01.ex07;

/**
 * Write a static method andThen that takes as parameters two Runnable instances
 * and returns a Runnable that runs the first, then the second. In the main
 * method, pass two lambda expressions into a call to andThen, and run the
 * returned instance.
 *
 * static メソッド andThen を書きなさい。andThen メソッドは、2 つの Runnable イ
 * ンスタンスをパラメータとして受け取るようにし、最初の Runnable を実行した後に 2 つ目の Runnable を実行する Runnable
 * を返すようにします。main メソッドでは、 andThen への呼び出しに 2 つのラムダ式を渡して、返されたインスタンスを実行するよ
 * うにしなさい。
 */
public class Runnables {

    private Runnables() {
    } // Non-instantiable

    /**
     * Constructs a Runnable which run the first Runnable, and then the second
     * Runnable.
     *
     * @param first Runnable to be run first.
     * @param second Runnable to be run then
     * @return Runnable which runs the both first and second, sequentially.
     */
    public static Runnable andThen(Runnable first, Runnable second) {
        return () -> {
            first.run();
            second.run();
        };
    }
}
