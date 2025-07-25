package com.fResult.ws.chat

import java.time.Instant

class Message(val clientId: String, val text: String, val `when`: Instant)
