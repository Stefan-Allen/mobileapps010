package me.stefanallen.chatapp

// Users in the chat application
class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null

    constructor() {
        // required for Firebase Realtime Database
    }

    // Creating a User object with name, email, and UID
    constructor(name: String?, email: String?, uid: String?) {
        this.name = name
        this.email = email
        this.uid = uid
    }
}
