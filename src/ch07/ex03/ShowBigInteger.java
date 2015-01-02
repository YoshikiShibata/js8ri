/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch07.ex03;

import java.math.BigInteger;

public class ShowBigInteger {
    
    public static void main(String[] args) {
        BigInteger b = new BigInteger("1234567890987654321");
        System.out.println(b);
        System.out.println((long)b.doubleValue());
        System.out.println(String.format("%s", b));
    }
    
}
