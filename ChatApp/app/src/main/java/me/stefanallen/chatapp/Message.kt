package me.stefanallen.chatapp

// Class representing a chat message
class Message {
    var message: String? = null
    var senderId: String? = null

    constructor() {
        //Required for Firebase Realtime Database
    }

    // Creating a Message object with message content and sender ID
    constructor(message: String?, senderId: String?) {
        this.message = message
        this.senderId = senderId
    }
}