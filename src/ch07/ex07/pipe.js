// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.

// run as jjs -scripting pipe.js

function pipe() {
    if (arguments.length == 0)
        return 'Illegal Arugments: zero legnth'

    var output = runAsFirstCommand(arguments[0])

    for (i = 1; i < arguments.length; i++) {
        output = runAsIntermediateProcess(arguments[i], output)
    }

    return inputStreamToString(output)
}

function runAsIntermediateProcess(command, input) {
    var pb = new java.lang.ProcessBuilder(command.split(' '));
    var p = pb.start()
    startStreamThread(input, p.getOutputStream())
    return p.getInputStream()
}

function runAsFirstCommand(command) {
    var pb = new java.lang.ProcessBuilder(command.split(' '));
    var p = pb.start()
    return p.getInputStream()
}

function inputStreamToString(input) {
    var reader = new java.io.InputStreamReader(input)
    var bufferedReader = new java.io.BufferedReader(reader)
    var ch
    var result = ''
    while ((ch = bufferedReader.read()) != -1) {
        result = result + String.fromCharCode(ch)
    }
    return result
}

function startStreamThread(input, output) {
    var runnable = function () {
        var ch
        while ((ch = input.read()) != -1) {
            output.write(ch);
        }
        output.close()
    }
    var thread = new java.lang.Thread(runnable)
    thread.start()
}

print(pipe())
print(pipe('ls -al'))
print(pipe('find .', 'grep js'))
print(pipe('find .', 'grep js', 'xargs grep Copyright'))