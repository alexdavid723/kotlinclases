package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.inscritos


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import java.time.format.DateTimeFormatter
import pe.edu.upeu.asistenciaupeujc.modelo.Inscrito
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.FabItem
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.LoadingCard
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.MultiFloatingActionButton

@Composable
fun InscritoUI (navegarEditarInsc: (String) -> Unit, viewModel:
InscritoViewModel = hiltViewModel(), navController: NavHostController
){
    val actis by viewModel.inscri.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    Log.i("VERX", ""+actis!!.size )

    MyApp(navController, onAddClick = {
        //viewModel.addUser()
        navegarEditarInsc((0).toString())
    }, onDeleteClick = {
        viewModel.deleteInscrito(it)
    }, actis, isLoading,
        onEditClick = {
            val jsonString = Gson().toJson(it)
            navegarEditarInsc(jsonString)
        }
    )
}


val formatoFecha: DateTimeFormatter? = DateTimeFormatter.ofPattern("dd-MM-yyyy")

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(navController: NavHostController,
          onAddClick: (() -> Unit)? = null,
          onDeleteClick: ((toDelete: Inscrito) -> Unit)? = null,
          inscritos: List<Inscrito>,
          isLoading: Boolean,
          onEditClick: ((toPersona: Inscrito) -> Unit)? = null,
) {
    val context = LocalContext.current
    //val navController = rememberNavController()
    val navigationItems2 = listOf(
        Destinations.ActividadUI,
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
        Destinations.InscritoUI
    )
    /*  val scaffoldState = rememberScaffoldState(
          drawerState = rememberDrawerState(initialValue =
          DrawerValue.Closed)
      )*/

    val fabItems = listOf(
        FabItem(
            Icons.Filled.ShoppingCart,
            "Shopping Cart"
        ) {
            val toast = Toast.makeText(context, "Hola Mundo", Toast.LENGTH_LONG) // in Activity
            toast.view!!.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN)
            toast.show()
        },
        FabItem(
            Icons.Filled.Favorite,
            "Add insccription"
        ) { onAddClick?.invoke() }
    )

    Scaffold(
        bottomBar = {
            BottomAppBar {
                BottomNavigationBar(navigationItems2, navController = navController)
            }
        },
        modifier = Modifier,
        floatingActionButton = {
            MultiFloatingActionButton(
                navController=navController,
                fabIcon = Icons.Filled.Add,
                items = fabItems,
                showLabels = true
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(modifier = Modifier
                .padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
                .align(alignment = Alignment.TopCenter),
                //.offset(x = (16).dp, y = (-32).dp),
                userScrollEnabled= true,
            ){
                var itemCount = inscritos.size
                if (isLoading) itemCount++
                items(count = itemCount) { index ->
                    var auxIndex = index;
                    if (isLoading) {
                        if (auxIndex == 0)
                            return@items LoadingCard()
                        auxIndex--
                    }
                    val inscrito = inscritos[auxIndex]
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp
                        ),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                    ) {

                    }
                }
            }

        }

    }
}
