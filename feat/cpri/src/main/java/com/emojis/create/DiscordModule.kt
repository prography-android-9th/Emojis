package com.emojis.create

import dev.kord.core.Kord
import kotlinx.coroutines.runBlocking

object DiscordModule {
    val KordInstance: Kord
        get() = runBlocking {  Kord(token = "") {
        }
    }
}