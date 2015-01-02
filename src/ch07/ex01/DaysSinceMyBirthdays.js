// jjs < DaysSinceMyBirtdays.js
var birthday = java.time.LocalDate.of(1959, 11, 17)
var now = java.time.LocalDate.now()
birthday.until(now, java.time.temporal.ChronoUnit.DAYS)