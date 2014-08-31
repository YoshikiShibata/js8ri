/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package js8ri.util;

import java.io.File;

/**
 * Contains utility methods for all exercises.
 * 
 * @author yoshiki
 */
public class Directories {
    /**
     * Converts the specified path into directory. If the path is not directory,
     * then <code>null</code> will be returned.
     * 
     * @param directoryPath path to be directory
     * @return File object representing the directory or <code>null</code> if 
     * the specified path neither exists nor is directory.
     */
    public static File toDirectory(String directoryPath) {
        if (directoryPath.isEmpty()) {
            System.out.println("empty directory path");
            return null;
        }
        
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            System.out.printf("Not Found : %s%n", directoryPath);
            return null;
        }
        
        if (!dir.isDirectory()) {
            System.out.printf("Not Directory : %s%n", directoryPath);
            return null;
        }
        return dir;
    }
}
