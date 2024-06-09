package com.assignment.passwordmanager.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val accountName: String,
    val username: String,
    val password: ByteArray,
    val timestamp: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int?= null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (accountName != other.accountName) return false
        if (username != other.username) return false
        if (!password.contentEquals(other.password)) return false
        if (timestamp != other.timestamp) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accountName.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + password.contentHashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + (id ?: 0)
        return result
    }
}


class InvalidDataFieldException(message: String): Exception(message)
