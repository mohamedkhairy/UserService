import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.userservice.presentation.home.AllUsersHomeScreen
import com.example.userservice.presentation.details.UserDetailsScreen
import com.example.userservice.presentation.sevice.MyServiceViewModel

@Composable
fun NavigationSystem(serviceViewModel: MyServiceViewModel) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Route.HOME.name) {
        composable(Route.HOME.name) { AllUsersHomeScreen(navController) }
        composable(Route.DETAILS.name) { UserDetailsScreen(navController, serviceViewModel) }
    }
}


enum class Route {
    HOME,
    DETAILS
}