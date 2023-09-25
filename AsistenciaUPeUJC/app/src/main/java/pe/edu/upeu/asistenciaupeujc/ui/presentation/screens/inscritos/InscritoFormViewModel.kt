package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscritos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.repository.ActividadRepository
import pe.edu.upeu.asistenciaupeujc.repository.InscritoRepository
import javax.inject.Inject

@HiltViewModel
class InscritoFormViewModel @Inject constructor(
    private val inscrep: InscritoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getInscrito(idX: Long): LiveData<Inscrito> {
        return inscrep.buscarInscritoId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addInscrito(inscrito: Inscrito){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", inscrito.toString())
            inscrep.insertarInscrito(inscrito)
        }
    }
    fun editInscrito(inscrito: Inscrito){
        viewModelScope.launch(Dispatchers.IO){
            inscrep.modificarRemoteInscrito(inscrito)
        }
    }
}