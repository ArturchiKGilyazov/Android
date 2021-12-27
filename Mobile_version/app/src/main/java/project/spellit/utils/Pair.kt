package project.spellit.utils

class Pair<T, K>(
    var first: T,
    var second: K
) {

    @JvmName("setFirst1")
    fun setFirst(value: T) {
        first = value
    }

    @JvmName("setSecond1")
    fun setSecond(value: K) {
        second = value
    }

    @JvmName("getFirst1")
    fun getFirst(): T {
        return first
    }

    @JvmName("getSecond1")
    fun getSecond(): K {
        return second
    }
}