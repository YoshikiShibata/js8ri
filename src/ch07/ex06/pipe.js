// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.

// run as jjs -scripting pipe.js

function pipe() {
    if (arguments.length == 0)
        return 'Illegal Arugments: zero legnth'
    
    var stdout = $EXEC(arguments[0])
    for (i = 1; i < arguments.length; i++) {
        stdout = $EXEC(arguments[i], stdout)
    }
    
    return stdout
}

print(pipe())
print(pipe('ls -al'))
print(pipe('find .', 'grep js', 'xargs grep Copyright'))