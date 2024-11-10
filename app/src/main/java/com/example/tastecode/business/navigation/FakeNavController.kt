import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/*
    This will be used for passing the NavController
    as Preview for the Parameter
*/

@Composable
fun fakeNavController(): NavHostController {
    return rememberNavController()
}