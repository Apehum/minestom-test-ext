package com.github.apehum.minestomext

import net.hollowcube.minestom.extensions.ExtensionBootstrap
import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.Event
import net.minestom.server.event.EventNode
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent
import java.util.function.Consumer

fun main() {
    val minecraftServer = ExtensionBootstrap.init()

    val instanceManager = MinecraftServer.getInstanceManager()
    val defaultInstance = instanceManager.createInstanceContainer()

    val eventHandler = MinecraftServer.getGlobalEventHandler()
    eventHandler.addListener<AsyncPlayerConfigurationEvent> { event ->
        val player = event.player

        player.respawnPoint = Pos(0.0, 0.0, 0.0)
        event.spawningInstance = defaultInstance
    }

    minecraftServer.start("0.0.0.0", 25565)
}

private inline fun <reified E : Event> EventNode<Event>.addListener(handler: Consumer<E>) = addListener(E::class.java, handler)
