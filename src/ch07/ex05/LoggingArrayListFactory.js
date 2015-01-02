// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
// 
// run as jjs LoggingArrayListFactory.js

function newLoggingArrayList() {
    var arr = new (Java.extend(java.util.ArrayList)) {
        add: function(x) {
            print('Adding ' + x)
            return Java.super(arr).add(x)
        }
    }
    return arr
}

var a1 = newLoggingArrayList()
var a2 = newLoggingArrayList()

a1.add('Hello')
a2.add('World')
print(a1.size())
print(a2.size())