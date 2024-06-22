package br.com.unisep.myalbum.album.newAlbum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import br.com.unisep.myalbum.databinding.DialogNewAlbumBinding
import br.com.unisep.myalbum.domain.dto.NewAlbumDto
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

typealias OnSaveAlbum = (NewAlbumDto) -> Unit

class AddNewAlbumDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogNewAlbumBinding
    lateinit var onSave: OnSaveAlbum

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogNewAlbumBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.btnSave.setOnClickListener { save() }
    }

    private fun save() {
        val album = NewAlbumDto(
            binding.etAlbumTitle.text.toString()

        )

        onSave(album)
        dialog?.dismiss()
    }

    companion object {

        @JvmStatic
        fun createAndShow(fragmentManager: FragmentManager) {
            val dialog = AddNewAlbumDialog()
            dialog.show(fragmentManager, "new-album")
        }
    }


}
