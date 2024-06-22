package br.edu.unisep.excuser.ui.excuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.edu.unisep.excuser.R
import br.edu.unisep.excuser.databinding.ActivityExcuseBinding
import br.edu.unisep.excuser.domain.dto.excuse.ExcuseDto
import br.edu.unisep.excuser.ui.category.CategoryDialog
import br.edu.unisep.excuser.ui.excuse.viewmodel.ExcuseViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExcuseActivity : AppCompatActivity() {

    private val binding: ActivityExcuseBinding by lazy {
        ActivityExcuseBinding.inflate(layoutInflater)
    }

    private val viewModel: ExcuseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
        setupObserver()

        loadExcuse()
    }

    private fun setupView() {
        binding.srlReload.setOnRefreshListener { loadExcuse() }
        binding.btnCategory.setOnClickListener { showCategories() }
    }

    private fun setupObserver() {
        viewModel.excuse.observe(this, ::onExcuseResult)
        viewModel.excuseError.observe(this) { onExcuseError() }
    }

    private fun loadExcuse() {
        binding.tvExcuse.visibility = View.GONE
        binding.tvCategory.visibility = View.GONE
        binding.pbLoading.visibility = View.VISIBLE
        binding.srlReload.isRefreshing = false

        viewModel.getExcuse()
    }

    private fun showCategories() {
        val dialog = CategoryDialog.create(::onCategorySelected)
        dialog.show(supportFragmentManager, TAG_DIALOG_CATEGORY)
    }

    private fun onCategorySelected(category: String?) {
        viewModel.category = category
        loadExcuse()
    }

    private fun onExcuseResult(excuse: ExcuseDto) {
        binding.tvExcuse.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.pbLoading.visibility = View.GONE

        binding.tvExcuse.text = excuse.content
        binding.tvCategory.text = excuse.category
    }

    private fun onExcuseError() {
        Snackbar.make(binding.root, R.string.defaultErrorMessage, Snackbar.LENGTH_LONG).show()
    }

    companion object {
        private const val TAG_DIALOG_CATEGORY = "category-dialog"
    }
}