package com.example.grubgamble

class User {

    var user: String = ""
        get() = field
        set(value) { field = value}

    var email: String = ""
        get() = field
        set(value) { field = value}

    var points: Int = 0
        get() = field
        set(value) { field = value}

    override fun toString(): String {
        return "User(user='$user', email='$email', points=$points)"
    }

}