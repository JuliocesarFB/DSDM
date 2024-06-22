package br.edu.unisep.excuser.ui.excuse.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.unisep.excuser.domain.dto.excuse.ExcuseDto
import br.edu.unisep.excuser.domain.dto.exception.DomainException
import br.edu.unisep.excuser.domain.repository.excuse.ExcuseRepository
import kotlinx.coroutines.launch

class ExcuseViewModel(private val repository: ExcuseRepository) : ViewModel() {

    var category: String? = null

    private val _excuse = MutableLiveData<ExcuseDto>()
    val excuse: LiveData<ExcuseDto>
        get() = _excuse

    private val _excuseError: MutableLiveData<DomainException> = MutableLiveData()
    val excuseError: LiveData<DomainException>
        get() = _excuseError

    fun getExcuse() {
        viewModelScope.launch {
            val result = repository.getExcuse(category)
            result.handleResult(
                { _excuse.postValue(it) },
                { _excuseError.postValue(it) }
            )
        }
    }
}