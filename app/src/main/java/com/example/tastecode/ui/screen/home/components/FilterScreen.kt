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
import androidx.compose.foundation.shape.RoundedCornerShape
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


@Composable
fun FilterColumn(
    onApplyFilter:(String,String,String,String)->Unit
) {

    val selectedCategory = remember { mutableStateOf<String?>(null) }
    val selectedDifficulty = remember { mutableStateOf<String?>(null) }
    val selectedTime = remember { mutableStateOf<String?>(null) }
    val selectedRating = remember { mutableStateOf<String?>(null) }


    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Filter Title
            Text(
                text = "Filter Options",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF129575)
            )


            Spacer(modifier = Modifier.height(24.dp))

            // Category: Type
            FilterLabelRow(
                label = "Type",
                labels = listOf("Dessert", "Main Course", "Appetizer"),
                selectedLabel = selectedCategory,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Difficulty
            FilterLabelRow(
                label = "Difficulty",
                labels = listOf("Easy", "Medium", "Hard"),
                selectedLabel = selectedDifficulty,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Cooking Time
            FilterLabelRow(
                label = "Cooking Time",
                labels = listOf("15-30 min", "30-60 min", "60+ min"),
                selectedLabel = selectedTime,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Rating
            FilterLabelRow(
                label = "Rating",
                labels = listOf("1 Star", "2 Stars", "3 Stars", "4 Stars", "5 Stars"),
                selectedLabel = selectedRating,
            )

        }

        Column(modifier = Modifier.align(Alignment.BottomCenter).padding(20.dp)) {
            UserActionButton(
                text = "Apply Filter", onClick = {
                    onApplyFilter(
                        selectedCategory.toString(),
                        selectedDifficulty.toString(),
                        selectedTime.toString(),
                        selectedRating.toString()
                    )
                })
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
                    label = item,
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
