/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex13;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Repeatable annotation for TestCase annotation
 */
@Retention(RetentionPolicy.SOURCE)
public @interface TestCases {
    TestCase[] value();
}
