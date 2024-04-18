package xyz.mgsip.main.model

data class Server(
    var serverId:Int,
    var serverName:String,
    var serverIp:String,
    var serverPort:Int,
    var serverGame:String,
    var previewImage:String?,
    var checkDb:Int
)
