import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ViewSidebar
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tastecode.business.utilities.BusinessUtils
import com.example.tastecode.business.utilities.BusinessUtils.executeInBackground
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val onBackPressedDispatcher =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.padding(vertical = 12.dp),
                onClick = {
                    onBackPressedDispatcher?.onBackPressed()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Sidebar",
                    tint = Color(0xFF129575)
                )
            }

            IconButton(
                modifier = Modifier
                    .padding(vertical = 12.dp),
                onClick = {
                    onBackPressedDispatcher?.onBackPressed()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Share",
                    tint = Color(0xFF129575)
                )
            }
        }


        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
        ) {
            Text(
                text = "Hello,${SharedData.userData?.firstName}",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black,
                ),
            )
        }



        RecipeList(navHostController = navController)
    }

}