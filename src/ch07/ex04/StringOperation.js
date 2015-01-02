// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.

var sub = 'Hello World'.substring(3, 7) + ' X'
print(sub.getClass()) // class java.lang.String
java.lang.String.class.cast(sub) // just works now