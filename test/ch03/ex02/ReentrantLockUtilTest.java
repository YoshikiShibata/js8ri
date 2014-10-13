/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class ReentrantLockUtilTest {

    @Test(expected=NullPointerException.class)
    public void testWithLockWithNullLock() {
        ReentrantLockUtil.withLock(null, null);
    }
    
    @Test(expected=NullPointerException.class)
    public void testWithLockWithNullAction() {
        ReentrantLock lock = new ReentrantLock();
        
        ReentrantLockUtil.withLock(lock, null);
    }
    
    @Test(expected=IllegalStateException.class)
    public void testLockAlreadyHeld() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        
        ReentrantLockUtil.withLock(lock, () -> { });
    }
    
    @Test
    public void testWithLock() {
        // Prepare
        boolean[] actionPerformed = new boolean[1];
        ReentrantLock lock = new ReentrantLock();
        
        // Action & Partial Check
        ReentrantLockUtil.withLock(lock, 
                () -> {
                    assertTrue(lock.isLocked());
                    actionPerformed[0] = true;
                });
        
        // Final Check
        assertTrue(!lock.isLocked());
        assertTrue(actionPerformed[0]);
    }
}
