// Copyright (C) 2015 Yoshiki Shibata. All rights reserved.

// run as jjs -fx PieChart.js

// This script was not easy to implement, mainly because no type checking at creating.

var dataList = readPieChartDataList()
var chart = createPieChart(dataList)
var group = new javafx.scene.Group(chart)
$STAGE.width = 500;
$STAGE.height = 500;
$STAGE.scene = new javafx.scene.Scene(group);

// Creates a PieChar from chart data list
function createPieChart(charDataList) {
    var chart = new javafx.scene.chart.PieChart(charDataList)
    chart.setTitle("Population of the Continents")

    return chart
}

// Reads the PieChart data as a ObservableList
function readPieChartDataList() {
    var path = java.nio.file.Paths.get('PieChartData.txt')
    var lines = trimLines(java.nio.file.Files.readAllLines(path))

    var pieChartDataArrayType = Java.type('javafx.scene.chart.PieChart.Data[]')
    var pieChartDataArray = new pieChartDataArrayType(lines.size());

    for (i = 0; i < lines.size(); i++) {
        var tokens = lines[i].split(':')

        pieChartDataArray[i] = new javafx.scene.chart.PieChart.Data(
                tokens[0].trim(),
                parseFloat(tokens[1].trim()))
    }
    return javafx.collections.FXCollections.observableArrayList(pieChartDataArray)
}

// Skips invalid data line
function trimLines(lines) {
    for (i = 0; i < lines.size(); ) {
        if (lines[i].length == 0 ||
            lines[i].split(':').length != 2) {
            lines.remove(i)
            continue;
        }
        i++
    }
    return lines
}

