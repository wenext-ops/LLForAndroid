package com.tenext.auth.message.upload

class ArrayString {

    private var array = ArrayList<String>()

    constructor()

    constructor(string: String) {
        array.add(string)
    }

    fun size(): Int {
        return array.size
    }

    fun isEmpty(): Boolean {
        return array.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return array.isNotEmpty()
    }

    fun addValue(value: String) {
        array.add(value)
    }

    fun clear() {
        array.clear()
    }

    override fun toString(): String {
        val sb = StringBuilder("[\"")
        array.forEachIndexed { index, s ->
            sb.append(s).append("\"")
            if (index < array.size - 1)
                sb.append(",\"")
        }
        sb.append("]")
        return sb.toString()
    }
}