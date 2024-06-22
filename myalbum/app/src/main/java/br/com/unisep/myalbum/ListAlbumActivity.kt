package br.com.unisep.myalbum

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.core.content.FileProvider
import br.com.unisep.myalbum.album.newAlbum.AddNewAlbumDialog
import br.com.unisep.myalbum.album.newAlbum.AddNewAlbumDialog.Companion.createAndShow
import br.com.unisep.myalbum.databinding.ActivityListAlbumBinding
import br.com.unisep.myalbum.databinding.DialogNewAlbumBinding
import br.com.unisep.myalbum.domain.dto.NewAlbumDto
import java.io.File

class ListAlbumActivity : AppCompatActivity() {

    private val binding: ActivityListAlbumBinding by lazy {
        ActivityListAlbumBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setupView()
    }

    private fun setupView(){
        binding.btnAdd.setOnClickListener{ openDialog()}
    }

    private fun openDialog(){
        AddNewAlbumDialog.createAndShow(supportFragmentManager)

    }

}