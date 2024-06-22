package br.com.unisep.myalbum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import br.com.unisep.myalbum.databinding.ActivityListAlbumBinding
import br.com.unisep.myalbum.databinding.ActivityListPhotosBinding
import br.com.unisep.myalbum.databinding.ItemPhotosBinding
import java.io.File

class ListPhotosActivity : AppCompatActivity() {

    private lateinit var photoUri: Uri

    private val binding: ActivityListPhotosBinding by lazy {
        ActivityListPhotosBinding.inflate(layoutInflater)
    }

    private val bindingPhoto: ItemPhotosBinding by lazy {
        ItemPhotosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupEvents()
    }

    private fun setupEvents(){
        binding.fabCamera.setOnClickListener { openCamera() }
    }

    private fun openCamera(){
        val photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val photoFile = File.createTempFile("photo_", ".jpg", photoDir)
        photoUri = FileProvider.getUriForFile(this,
            "br.edu.unisep.myphotos.fileprovider", photoFile)

        cameraLauncher.launch(photoUri)
    }

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()){ pictureTaken ->

        if (pictureTaken){
            bindingPhoto.ivPhoto.setImageURI(photoUri)
        }
    }
}