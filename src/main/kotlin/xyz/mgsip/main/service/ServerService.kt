package xyz.mgsip.main.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.S3Client
import xyz.mgsip.main.util.FileUploadUtil
import xyz.mgsip.main.dao.ServerDao
import xyz.mgsip.main.model.ApiResult
import xyz.mgsip.main.model.Server

interface ServerService {
    abstract fun getServerList(): List<Server>
    abstract fun putServer(multipartFile:MultipartFile?,server: Server): ApiResult
    abstract fun deleteServer(serverId: Int)

}


@Service
class ServerServiceImpl(private val serverDao: ServerDao,
                         private val fileUploadUtil: FileUploadUtil):ServerService{

    override fun getServerList(): List<Server> {

        val servers:List<Server> = serverDao.getServerList()
        return servers
    }

    @Transactional
    override fun putServer(multipartFile:MultipartFile?,server: Server): ApiResult {

        if(server.serverId == null)
        {
            if(multipartFile != null)
            {
                 fileUploadUtil.putGamePreviewImage(multipartFile)
            }
            serverDao.addServer(server)

            return ApiResult(message = "서버 정보 추가가 완료되었습니다.",
                HttpStatus.CREATED)
        }
        else
        {
            serverDao.updateServer(server)
            return ApiResult(message = "서버 정보 업데이트가 완료되었습니다.",
                HttpStatus.OK)
        }
    }

    override fun deleteServer(serverId: Int) {
        serverDao.deleteServer(serverId)
    }
}