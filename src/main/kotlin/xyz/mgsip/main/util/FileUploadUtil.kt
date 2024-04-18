package xyz.mgsip.main.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.S3Client

interface FileUploadUtil {
    abstract fun putGamePreviewImage(multipartFile: MultipartFile)
    abstract fun putProfilePreviewImage(multipartFile: MultipartFile)
}

@Component
class  FileUploadUtilImpl(private val s3Client: S3Client): FileUploadUtil {

    @Value("\${spring.cloud.aws.s3.bucket}")
    private val bucket: String? = null
    override fun putGamePreviewImage(multipartFile: MultipartFile) {
        TODO("Not yet implemented")
    }

    override fun putProfilePreviewImage(multipartFile: MultipartFile) {
        TODO("Not yet implemented")
    }
}

