package pe.edu.upeu.asistenciaupeujc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad

@Database(entities = [Actividad::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
}