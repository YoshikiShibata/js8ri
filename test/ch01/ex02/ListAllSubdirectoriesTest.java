/*
 * Copyright (C) 2014, 2017, 2019 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex02;

import java.io.File;
import js8ri.util.Directories;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test code for ListAllSubdirectories. This code must be run on a Unix such
 * as Linux or Mac OS X.
 */
public class ListAllSubdirectoriesTest {
    private final String userHome = System.getProperty("user.home");

    @Test
    public void listAllSubDirectories() {
        // Prepare
        File dir = Directories.toDirectory(userHome);
        assertNotNull(dir);

        // Action
        File[] subdirectories = ListAllSubdirectories.listAllSubDirectories(dir);

        // Check
        for (File sub : subdirectories) {
            if (!sub.isDirectory()) {
                fail(sub.getName() + " is not a directory");
            }
        }
    }

    @Test
    public void listAllSubDirectoriesWithMethodReference() {
        // Prepare
        File dir = Directories.toDirectory(userHome);
        assertNotNull(dir);

        // Action
        File[] subdirectories = ListAllSubdirectories.listAllSubDirectoriesWithMethodReference(dir);

        // Check
        for (File sub : subdirectories) {
            if (!sub.isDirectory()) {
                fail(sub.getName() + " is not a directory");
            }
        }
    }

}
