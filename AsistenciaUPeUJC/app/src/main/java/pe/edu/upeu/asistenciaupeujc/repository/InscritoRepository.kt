package pe.edu.upeu.asistenciaupeujc.repository


import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.InscritoDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestActividad
import pe.edu.upeu.asistenciaupeujc.data.remote.RestInscrito
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import javax.inject.Inject


interface InscritoRepository {
    suspend fun deleteInscrito(inscrito: Inscrito)
    fun reportarInscritos(): LiveData<List<Inscrito>>

    fun buscarInscritoId(id:Long): LiveData<Inscrito>

    suspend fun insertarInscrito(inscrito: Inscrito):Boolean

    suspend fun modificarRemoteInscrito(inscrito: Inscrito):Boolean
}

class InscritoRepositoryImpl @Inject constructor(
    private val restInscrito: RestInscrito,
    private val inscritoDao: InscritoDao
): InscritoRepository{
    override suspend fun deleteInscrito(inscrito: Inscrito){
        CoroutineScope(Dispatchers.IO).launch {
            restInscrito.deleteInscrito(TokenUtils.TOKEN_CONTENT,inscrito.id)
        }
        inscritoDao.eliminarInscrito(inscrito)
    }


    override fun reportarInscritos():LiveData<List<Inscrito>>{
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                val data=restInscrito.reportarInscrito(TokenUtils.TOKEN_CONTENT).body()!!
                inscritoDao.insertarInscritos(data)
            }
        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return inscritoDao.reportatInscrito()
    }

    override fun buscarInscritoId(id:Long):LiveData<Inscrito>{
        return  inscritoDao.buscarInscrito(id)
    }


    override suspend fun insertarInscrito(inscrito: Inscrito):Boolean{
        return restInscrito.insertarInscrito(TokenUtils.TOKEN_CONTENT, inscrito).body()!=null
    }

    override suspend fun modificarRemoteInscrito(inscrito: Inscrito):Boolean{
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restInscrito.actualizarInscrito(TokenUtils.TOKEN_CONTENT, inscrito.id, inscrito).body()!=null
    }

}