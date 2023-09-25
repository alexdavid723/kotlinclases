package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscritos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.repository.InscritoRepository
import javax.inject.Inject

@HiltViewModel
class InscritoViewModel @Inject constructor(
    private val inscrrepo: InscritoRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val inscri: LiveData<List<Inscrito>> by lazy {
        inscrrepo.reportarInscritos()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addInscrito() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteInscrito(toDelete: Inscrito) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMinAR", toDelete.toString())
            inscrrepo.deleteInscrito(toDelete);
        }
    }

}