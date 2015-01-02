// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.

var contents = new java.lang.String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("alice.txt")), java.nio.charset.StandardCharsets.UTF_8)

var listOfWords = java.util.Arrays.asList(contents.split(/\W/))

var sortedSet = new java.util.TreeSet()
listOfWords.stream().filter(function(w) {return w.length() > 12 }).forEach(function(w) { sortedSet.add(w) })

sortedSet.stream().forEachOrdered(function(w) { print(w) })