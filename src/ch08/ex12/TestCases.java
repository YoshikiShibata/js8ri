/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex12;

/**
 * Repeatable annotation for TestCase annotation
 */
public @interface TestCases {
    TestCase[] value();
}
