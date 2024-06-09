package com.assignment.passwordmanager.core.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class CryptoManager {

    private val keystore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }


    private fun getKey():SecretKey{
        val existingKey = keystore.getEntry("secret", null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey{
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    "secret", KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                    )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    companion object{
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

    }

    fun encrypt(data: String): ByteArray{
        val encryptCipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, getKey())
        }
        encryptCipher.init(Cipher.ENCRYPT_MODE, getKey())
        val iv = encryptCipher.iv
        val encryptedBytes = encryptCipher.doFinal(data.toByteArray())

        val combinedBytes = ByteArray(iv.size + encryptedBytes.size)
        System.arraycopy(iv, 0, combinedBytes, 0, iv.size)
        System.arraycopy(encryptedBytes, 0, combinedBytes, iv.size, encryptedBytes.size)

        return combinedBytes
    }

    fun decrypt(bytes: ByteArray): String{
        val decryptCipher = Cipher.getInstance(TRANSFORMATION)
        val ivSize = decryptCipher.blockSize
        val iv = bytes.copyOfRange(0, ivSize)

        decryptCipher.init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        val decryptedBytes = decryptCipher.doFinal(bytes.copyOfRange(ivSize, bytes.size))
        return String(decryptedBytes)
    }
}