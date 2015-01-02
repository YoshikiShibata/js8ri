// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.

var b = new java.math.BigInteger('1234567890987654321')

b // b is treated as Number
b.mod(java.math.BigInteger.TEN) // invoke BigInteger.mod
java.lang.String.format('%s', b) // invoked BigInteger.toString
// BigInteger can be shwon with %d because it is treated as Numeric(Integral)
java.lang.String.format('%d', b)  