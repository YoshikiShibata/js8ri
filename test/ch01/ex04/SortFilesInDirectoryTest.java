/*
 * Copyright (C) 2014, 2019 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex04;

import java.io.File;
import js8ri.util.Directories;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test code for SortFilesInDirectory. This code must be run on a Unix such as
 * Linux or Mac OS X
 */
public class SortFilesInDirectoryTest {
    private final String userHome = System.getProperty("user.home");

    @Test
    public void sortFilesByDirectoryThenFilename() {
        // Prepare
        File[] files = Directories.listFiles(userHome);
        assertNotNull(files);
        assertTrue(files.length != 0);

        // Action
        SortFilesInDirectory.sortFilesByDirectoryThenFilename(files);

        // Check
        boolean modeChanged = false;

        for (int i = 1; i < files.length; i++) {
            File prev = files[i - 1];
            File next = files[i];

            if (prev.isDirectory() == next.isDirectory()) {
                if (prev.getName().compareTo(next.getName()) > 0) {
                    fail("Not Sorted" + prev.getName() + " " + next.getName());
                }
                continue;
            }

            if (modeChanged) {
                fail("Directory Mode changed twice");
            }

            assertTrue(prev.isDirectory());
            modeChanged = true;
        }
    }
}
