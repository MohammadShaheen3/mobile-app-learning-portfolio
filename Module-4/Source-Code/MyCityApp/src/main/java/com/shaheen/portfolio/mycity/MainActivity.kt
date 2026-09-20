package com.shaheen.portfolio.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Place(
    val id: Int,
    val name: String,
    val category: String,
    val summary: String,
    val description: String
)

data class CityUiState(
    val selectedPlace: Place? = null
)

object CityRepository {
    val places = listOf(
        Place(1, "Heritage Museum", "Culture", "Local history and exhibitions", "A calm place to explore the city's history, objects, stories, and changing identity."),
        Place(2, "Central Art Gallery", "Culture", "Modern and community art", "A gallery that highlights visual art, student work, and rotating community exhibitions."),
        Place(3, "Riverside Park", "Nature", "Walking paths and open space", "A green public space suited to walking, relaxing, and observing the city from a slower perspective."),
        Place(4, "Botanical Garden", "Nature", "Plants and shaded paths", "A landscaped garden where visitors can explore plant collections and enjoy a quiet outdoor environment."),
        Place(5, "Old Market", "Food", "Local snacks and everyday life", "A lively market environment where visitors can experience local food, small traders, and daily routines."),
        Place(6, "Night Food Street", "Food", "Evening street-food choices", "An evening destination with varied food stalls that demonstrates how public spaces change after dark.")
    )

    val categories: List<String> = places.map { it.category }.distinct()
    fun placesFor(category: String): List<Place> = places.filter { it.category == category }
    fun placeById(id: Int): Place? = places.find { it.id == id }
}

class CityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState.asStateFlow()

    fun selectPlace(id: Int) {
        _uiState.update { current -> current.copy(selectedPlace = CityRepository.placeById(id)) }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyCityApp()
                }
            }
        }
    }
}

@Composable
fun MyCityApp(cityViewModel: CityViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onCategorySelected = { category -> navController.navigate("category/$category") },
                onPlaceSelected = { id -> navController.navigate("detail/$id") }
            )
        }
        composable(
            route = "category/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category").orEmpty()
            CategoryScreen(
                category = category,
                onPlaceSelected = { id -> navController.navigate("detail/$id") },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "detail/{placeId}",
            arguments = listOf(navArgument("placeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("placeId") ?: -1
            LaunchedEffect(id) { cityViewModel.selectPlace(id) }
            val state by cityViewModel.uiState.collectAsState()
            DetailScreen(place = state.selectedPlace, onBack = { navController.popBackStack() })
        }
    }
}

@Composable
private fun HomeScreen(
    onCategorySelected: (String) -> Unit,
    onPlaceSelected: (Int) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val wideLayout = maxWidth >= 700.dp
        if (wideLayout) {
            Row(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                CategoryList(
                    modifier = Modifier.weight(0.35f).fillMaxHeight(),
                    onCategorySelected = onCategorySelected
                )
                Spacer(modifier = Modifier.width(20.dp))
                PlaceList(
                    title = "All recommendations",
                    places = CityRepository.places,
                    modifier = Modifier.weight(0.65f),
                    onPlaceSelected = onPlaceSelected
                )
            }
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                Text("My City Guide", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text("Choose a category", modifier = Modifier.padding(top = 8.dp, bottom = 16.dp))
                CategoryList(onCategorySelected = onCategorySelected)
            }
        }
    }
}

@Composable
private fun CategoryList(
    modifier: Modifier = Modifier,
    onCategorySelected: (String) -> Unit
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(CityRepository.categories) { category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelected(category) },
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(category, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("${CityRepository.placesFor(category).size} recommendations")
                }
            }
        }
    }
}

@Composable
private fun CategoryScreen(
    category: String,
    onPlaceSelected: (Int) -> Unit,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Button(onClick = onBack) { Text("Back") }
        PlaceList(
            title = category,
            places = CityRepository.placesFor(category),
            modifier = Modifier.padding(top = 16.dp),
            onPlaceSelected = onPlaceSelected
        )
    }
}

@Composable
private fun PlaceList(
    title: String,
    places: List<Place>,
    modifier: Modifier = Modifier,
    onPlaceSelected: (Int) -> Unit
) {
    Column(modifier = modifier) {
        Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        LazyColumn(
            modifier = Modifier.padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(places) { place ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPlaceSelected(place.id) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(place.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(place.summary, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailScreen(place: Place?, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp)) {
        Button(onClick = onBack) { Text("Back") }
        if (place == null) {
            Text("Place not found", modifier = Modifier.padding(top = 24.dp))
            return@Column
        }
        Text(
            place.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(place.category, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 8.dp))
        Text(place.description, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 20.dp))
    }
}
