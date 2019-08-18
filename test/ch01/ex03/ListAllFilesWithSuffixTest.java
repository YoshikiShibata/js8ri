/*
 * Copyright (C) 2014, 2019 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex03;

import java.io.File;
import js8ri.util.Directories;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test code for ListAllFilesWithSuffix. This code must be run on a Unix such as
 * Linux or Mac OS X.
 */
public class ListAllFilesWithSuffixTest {
    private final String userHome = System.getProperty("user.home");

    @Test
    public void listAllFilesWithSuffix() {
        // Prepare
        File dir = Directories.toDirectory(userHome);
        assertNotNull(dir);

        // Action
        String[] files = ListAllFilesWithSuffix.listAllFilesWithSuffix(dir, "h");

        // Check
        for (String name : files) {
            if (!name.endsWith(".h")) {
                fail(name + " Not Ended With .h");
            }
        }
    }
}
