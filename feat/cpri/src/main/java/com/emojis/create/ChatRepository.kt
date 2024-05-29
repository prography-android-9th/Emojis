package com.emojis.create

import android.util.Log
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Impl {

    private val module = DiscordModule

    private val scope = CoroutineScope(Dispatchers.IO)

    val itemMessages = module.KordInstance.on<MessageCreateEvent> {
        if (message.author?.isBot != false) return@on

        // check if our command is being invoked
        if (message.content != "!ping") return@on

        // all clear, give them the pong!
        message.channel.createMessage("pong!")

    }

    fun sendPrompt() {
        scope.launch {
        }
    }

    @OptIn(PrivilegedIntent::class)
    fun login() {
        scope.launch {
            module.KordInstance.login {
                Log.d("CPRI","MESSAGE CREATE EVENT")
                intents += Intent.MessageContent
                delay(5000)
//                module.KordInstance.rest.channel.createMessage(Snowflake(1245298569863237745)) {
//                    content = "!miimagine happy face emoji"
//                }
            }
        }
    }
}