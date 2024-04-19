package xyz.mgsip.main.model

data class Server(
    var serverId:Int? = null,
    var serverName:String,
    var serverIp:String,
    var serverPort:Int,
    var serverGame:String,
    var previewImage:String?,
    var checkDb:Int
)
