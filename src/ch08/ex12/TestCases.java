/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex12;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Repeatable annotation for TestCase annotation
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestCases {
    TestCase[] value();
}