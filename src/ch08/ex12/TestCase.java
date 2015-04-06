/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex12;

import java.lang.annotation.Repeatable;

/**
 * TestCase annotation for a method.
 */
@Repeatable(TestCases.class)
public @interface TestCase {
    int param();    // parameter
    int expected(); // expected return value
}
