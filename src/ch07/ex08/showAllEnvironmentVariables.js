// Copyright (C) 2015 Yoshiki Shibata. All Rights reserved.

// run as jjs -scripting showAllEnvironmentVariables.js

for (var key in $ENV) {
    print(key + '=' + $ENV[key])
}