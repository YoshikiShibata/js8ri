#!/usr/bin/jrunscript -f -
// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.

// run as jjs nextYear.js -- 55

if (arguments.length == 1) {
    print('Next year, you will be ' + (parseInt(arguments[0]) + 1))
} else if ('AGE' in $ENV) {
    print('Next year, you will be ' + (parseInt($ENV.AGE) + 1))
} else {
    print('Current Age is neither specified nor found')
}
        
