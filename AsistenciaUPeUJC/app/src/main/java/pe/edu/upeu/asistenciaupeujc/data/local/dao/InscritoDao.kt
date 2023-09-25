package pe.edu.upeu.asistenciaupeujc.data.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
@Dao
interface InscritoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarInscritos(inscrito: List<Inscrito>)

    @Update
    suspend fun modificarInscrito(inscrito: Inscrito)

    @Delete
    suspend fun eliminarInscrito(inscrito: Inscrito)

    @Query("select * from inscrito")
    fun reportatInscrito(): LiveData<List<Inscrito>>

    @Query("select * from inscrito where id=:idx")
    fun buscarInscrito(idx: Long): LiveData<Inscrito>
}