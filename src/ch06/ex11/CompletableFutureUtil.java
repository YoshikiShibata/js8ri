/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex11;

import java.io.Console;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write a method
 *
 * <pre>
 * public static <T> CompletableFuture<T> repeat( Supplier<T>
 * action, Predicate<T> until)
 * </pre>
 *
 * that asynchronously repeats the action until it produces a value that is
 * accepted by the until function, which should also run asynchronously. Test
 * with a function that reads a java.net.PasswordAuthentication from the
 * console, and a function that simulates a validity check by sleeping for a
 * second and then checking that the password is "secret". Hint: Use recursion.
 *
 * 次のメソッドを作成しなさい。
 *
 * このメソッドは、until 関数が受け入れる値を生成するまで、action を非同期 に繰り返します。until
 * 関数も非同期に実行されるべきです。コンソールから java.net.PasswordAuthentication を読み込む関数、および、1
 * 秒間スリープす ることで正当性検査をシミュレートし、パスワードが"secret"であるかを検査する関 数を用いてテストしなさい。ヒント：再帰を使用します。
 */
public class CompletableFutureUtil {

    private CompletableFutureUtil() {
        // non-instantiable
    }

    public static <T> CompletableFuture<T> repeat(
            Supplier<T> action, Predicate<T> until) {
        Objects.requireNonNull(action, "action is null");
        Objects.requireNonNull(until, "until is null");

        return CompletableFuture.supplyAsync(action).thenComposeAsync(
                (t) -> {
                    return until.test(t)
                            ? CompletableFuture.completedFuture(t)
                            : repeat(action, until);
                }
        );
    }

    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.out.println("Please run this program from the console");
            System.exit(1);
        }

        CompletableFuture<PasswordAuthentication> cp = repeat(
                () -> {
                    console.printf("User name: ");
                    String userName = console.readLine();
                    char[] password = console.readPassword("Password: ");
                    return new PasswordAuthentication(userName, password);
                },
                pa -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CompletableFutureUtil.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    char[] password = pa.getPassword();
                    char[] realPassword = {'s', 'e', 'c', 'r', 'e', 't'};
                    return Arrays.equals(password, realPassword);
                }
        );
        
        ForkJoinPool.commonPool().awaitQuiescence(60, TimeUnit.SECONDS);

    }

}
