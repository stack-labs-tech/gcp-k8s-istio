package com.stack_labs.workshop

import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.BucketInfo
import com.google.cloud.storage.StorageOptions
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.LoggerFactory
import java.nio.charset.Charset

@Controller("/")
class StorageController() {

    companion object {
        val LOG = LoggerFactory.getLogger(StorageController::class.java)!!
        val BUCKET = "writer_app"
    }

    @Get("/write")
    fun writeRandomFile(): HttpStatus {
        val storage = StorageOptions.getDefaultInstance().service

        // Create the bucket if it does not exist
        if (storage.list().values.none { bucket -> bucket.name == BUCKET })
            storage.create(BucketInfo.of(BUCKET))

        // Create a stupid file inside our bucket
        val fileContent = RandomStringUtils.randomAlphabetic(50).toByteArray(Charset.defaultCharset())
        val filename = RandomStringUtils.randomAlphabetic(8)
        storage.create(BlobInfo.newBuilder(BUCKET, filename).build(), fileContent)

        LOG.info("Created a new file '$filename' in bucket $BUCKET")

        return HttpStatus.CREATED
    }
}