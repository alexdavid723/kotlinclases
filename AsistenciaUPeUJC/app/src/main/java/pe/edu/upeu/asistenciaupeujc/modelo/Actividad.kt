package pe.edu.upeu.asistenciaupeujc.modelo

data class Actividad(
    var id: Long,
    var nombreActividad: String,
    var fecha: String,
    var horai: String,
    var minToler: String,
    var latitud: String,
    var longitud: String,
    var estado: String,
    var evaruar: String,
    var userCreate: String,
    var mater: String,
    var varidInsc: String,
    var asisSubact: String,
    var entsal: String,
    var offlinex: String,
    var asistenciaxs: List<Asistenciax>,
    var inscritos: List<Inscrito>,
    var subactasisxs: List<Subactasisx>,
    var materialesxs: List<Materialesx>,    
)
