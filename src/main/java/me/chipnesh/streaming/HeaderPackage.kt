package me.chipnesh.streaming

import me.chipnesh.streaming.DataPackage.Companion.BOUNDARY_SIGN

class HeaderPackage : DataPackage {

    override fun bytes(): ByteArray {
        return """HTTP/1.0 200 OK
Connection: close
Max-Age: 0
Expires: 0
Cache-Control: no-store, no-cache, must-revalidate, pre-check=0, post-check=0, max-age=0
Pragma: no-cache
Content-Type: multipart/x-mixed-replace;
boundary=--$BOUNDARY_SIGN


""".toByteArray()
    }
}
