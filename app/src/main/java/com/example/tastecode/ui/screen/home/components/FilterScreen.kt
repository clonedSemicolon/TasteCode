import android.widget.ScrollView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tastecode.ui.utils.Keywords


@Composable
fun FilterColumn(
    selectedCategory: MutableState<String?>,
    selectedDifficulty: MutableState<String?>,
    selectedTime: MutableState<String?>,
    selectedRating: MutableState<String?>,
    selectedServing:MutableState<String?>,
    showFilterScreen: MutableState<Boolean>,
    onApplyFilter: () -> Unit // Callback to apply filters
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Filter Options",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF129575)
            )

            Spacer(modifier = Modifier.height(24.dp))

            FilterLabelRow(
                label = "Type",
                labels = Keywords.recipeType,
                selectedLabel = selectedCategory,
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilterLabelRow(
                label = "Difficulty",
                labels = Keywords.difficultyList,
                selectedLabel = selectedDifficulty,
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilterLabelRow(
                label = "Cooking Time",
                labels = Keywords.preparationtimeList,
                selectedLabel = selectedTime,
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilterLabelRow(
                label = "Rating",
                labels = Keywords.ratingList,
                selectedLabel = selectedRating,
            )

            Spacer(modifier = Modifier.height(16.dp))

            FilterLabelRow(
                label = "Serving",
                labels = Keywords.servingSize,
                selectedLabel = selectedServing,
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {
            UserActionButton(
                text = "Apply Filter",
                onClick = {
                    onApplyFilter()
                    showFilterScreen.value = false
                }
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterLabelRow(
    label: String,
    labels: List<String>,
    selectedLabel: MutableState<String?> // Bind to selected label state
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF129575),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Row to display all labels horizontally
        FlowRow (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            labels.forEach { item ->
                FilterLabel(
                    label = if(label == "Rating") "$item Star" else item,
                    isSelected = item == selectedLabel.value,
                    onClick = { selectedLabel.value = if (selectedLabel.value == item) null else item }
                )
            }
        }
    }
}

@Composable
fun FilterLabel(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFF129575) else Color(0xFF129575)
    val backgroundColor = if (isSelected) Color(0xFF129575) else Color.White
    val textColor = if (isSelected) Color.White else Color(0xFF129575)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )
    }
}


data class FilterCriteria(
    val category: String? = null,
    val difficulty: String? = null,
    val time: String? = null,
    val rating: String? = null,
    val serving:String? = null
)

