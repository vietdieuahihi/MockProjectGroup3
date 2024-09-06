package com.example.client.internal

import com.example.server.entity.User

class SessionManager private constructor() {

    companion object {
        private var instance: SessionManager? = null

        fun getIns(): SessionManager {
            if (instance == null) {
                instance = SessionManager()
            }
            return instance!!
        }
    }

    var currentUser: User? = null
}