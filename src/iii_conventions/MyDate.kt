package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}

//operator fun MyDate.rangeTo(other: MyDate): DateRange = when {
//    this <= other -> DateRange(this, other)
//    else -> DateRange(other, this)
//}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate): Iterable<MyDate> {
    operator fun contains(date: MyDate): Boolean {
        return date >= start && date <= endInclusive
    }
    override operator fun iterator(): Iterator<MyDate> = MyDateIterator(this)
}

class MyDateIterator(val dateRange: DateRange): Iterator<MyDate> {
    private var pointer: MyDate = dateRange.start
    override fun hasNext(): Boolean = pointer <= dateRange.endInclusive
    override fun next(): MyDate {
        val current: MyDate = pointer
        pointer = pointer.nextDay()
        return current
    }
}
