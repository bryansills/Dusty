package android.util

val String.base64Encoded: String
    get() {
        return Base64.encodeToString(this.toByteArray(), Base64.NO_WRAP)
    }

val String.base64Decoded: String
    get() {
        val result = Base64.decode(this, Base64.NO_WRAP)
        return result.decodeToString()
    }