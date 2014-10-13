/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex02;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * When you use a ReentrantLock, you are required to lock and unlock with the
 * idiom
 *
 * ReentrantLock を使用する場合には、次のイデオムでロックとアンロックをする必要 があります。
 *
 * <blockquote><pre>
 * myLock.lock();
 * try {
 *     some action
 * } finally {
 *     myLock.unlock();
 * }
 * </pre></blockquote>
 *
 * Provide a method withLock so that one can call
 *
 * 次のように呼び出すことができるwithLock メソッドを提供しなさい。
 *
 * <blockquote><pre>
 * withLock(myLock, () -> { some action })
 * </pre></blockquote>
 */
public class ReentrantLockUtil {
    private ReentrantLockUtil() {
        // Non-instantiable
    }
    
    /**
     * Run an action while its associated lock is locked. After its exection, the 
     * lock will be unlocked
     * @param lock a lock to be held during the exection of the action
     * @param action action to be executed.
     * @throws NullPointerException if either lock or action is null.
     * @throws IllegalStateException if lock is already held by any thread
     */
    public static void withLock(ReentrantLock lock, Runnable action) {
        Objects.requireNonNull(lock, "lock is null");
        Objects.requireNonNull(action, "action is null");
        
        if (lock.isLocked())
            throw new IllegalStateException("lock is already held");
        
        lock.lock();
        try {
            action.run();
        } finally {
            lock.unlock();
        }
    }
}
