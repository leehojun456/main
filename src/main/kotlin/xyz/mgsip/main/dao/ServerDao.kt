package xyz.mgsip.main.dao

import xyz.mgsip.main.model.Server

interface ServerDao {
    abstract fun getServerList(): List<Server>
    abstract fun addServer(server: Server)
    abstract fun updateServer(server: Server)
}