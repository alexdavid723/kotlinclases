package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscritos



import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.gson.Gson
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBoxTwo
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DropdownMenuCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad.ActividadFormViewModel
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils

@Composable
fun InscritoForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: ActividadFormViewModel = hiltViewModel()
) {
    val inscritoD: Inscrito
    if (text!="0"){
        inscritoD = Gson().fromJson(text, Inscrito::class.java)
    }else{
        inscritoD= Inscrito(0,"","", "","","")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(inscritoD.id!!,
        darkMode,
        navController,
        inscritoD,
        viewModel
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(
    id:Long,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    inscrito: Inscrito,
    viewModel: ActividadFormViewModel
){

    Log.i("VERRR", "d: "+inscrito?.id!!)
    val person=Actividad(0,"","", "","","","","","","","", "", "", "", "")
    val context = LocalContext.current
    val scope = rememberCoroutineScope()


    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer()
                AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                    navController.navigate(Destinations.InscritoUI.route)
                }
            }
        }
    }
}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}