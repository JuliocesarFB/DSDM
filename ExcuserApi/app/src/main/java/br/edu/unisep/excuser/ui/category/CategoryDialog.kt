package br.edu.unisep.excuser.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import br.edu.unisep.excuser.R
import br.edu.unisep.excuser.databinding.DialogCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

typealias OnCategoryResult = (String?) -> Unit

class CategoryDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCategoryBinding
    private var onResult: OnCategoryResult? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        onResult = arguments?.get(PARAM_ON_RESULT) as? OnCategoryResult?

        binding.rgCategories.setOnCheckedChangeListener { _, itemId ->
            when(itemId) {
                R.id.rbRandom -> onCategorySelected(null)
                R.id.rbFamily -> onCategorySelected("family")
                R.id.rbOffice -> onCategorySelected("office")
                R.id.rbChildren -> onCategorySelected("children")
                R.id.rbCollege -> onCategorySelected("college")
                R.id.rbParty -> onCategorySelected("party")
            }

        }
    }

    private fun onCategorySelected(category: String?) {
        onResult?.invoke(category)
        dismiss()
    }

    companion object {
        private const val PARAM_ON_RESULT = "onResult"

        fun create(onResult: OnCategoryResult) =
            CategoryDialog().apply {
                arguments = bundleOf(PARAM_ON_RESULT to onResult)
            }
    }

}

