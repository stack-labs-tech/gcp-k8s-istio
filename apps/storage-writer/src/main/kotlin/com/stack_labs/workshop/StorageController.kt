package com.stack_labs.workshop

import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.BucketInfo
import com.google.cloud.storage.StorageClass
import com.google.cloud.storage.StorageOptions
import io.micronaut.context.annotation.Value
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import org.apache.commons.lang3.RandomStringUtils
import org.slf4j.LoggerFactory
import java.nio.charset.Charset

@Controller("/")
class StorageController(@Value("\${BUCKET}") private val bucket: String?) {

    companion object {
        val LOG = LoggerFactory.getLogger(StorageController::class.java)!!

    }

    @Get("/write")
    fun writeRandomFile(): HttpStatus {
        val bucketName = bucket ?: "gke_training_" + RandomStringUtils.randomAlphabetic(10).toLowerCase()
        val storage = StorageOptions.getDefaultInstance().service

        // Create the bucket if it does not exist
        if (storage.list().values.none { bucket -> bucket.name == bucketName }) {
            storage.create(BucketInfo.newBuilder(bucketName)
                    .setLocation("")
                    .setStorageClass(StorageClass.REGIONAL)
                    .setLocation("europe-west1")
                    .build())
        }

        // Create a stupid file inside our bucket
        val fileContent = RandomStringUtils.randomAlphabetic(50).toByteArray(Charset.defaultCharset())
        val filename = RandomStringUtils.randomAlphabetic(8)
        storage.create(BlobInfo.newBuilder(bucketName, filename).build(), fileContent)

        LOG.info("Created a new file '$filename' in bucket $bucketName")

        return HttpStatus.CREATED
    }
}