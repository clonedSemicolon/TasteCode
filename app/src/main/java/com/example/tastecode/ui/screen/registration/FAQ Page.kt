import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tastecode.R
import androidx.compose.animation.animateContentSize

// Color Scheme
private val LightGreenColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50), // Green primary color
    onPrimary = Color.White,
    background = Color(0xFFF0FDF4), // Light green background
    onBackground = Color(0xFF1B5E20), // Dark green for text/icons
    surface = Color.White,
    onSurface = Color(0xFF1B5E20)
)

@Composable
fun FAQPage(navController: NavController) {
    MaterialTheme(colorScheme = LightGreenColorScheme) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(24.dp))
                // FAQ Header
                Text(
                    text = "FAQ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                )

                // FAQ Items
                FAQItem(
                    question = "How do I reset my password?",
                    answer = "You can reset your password by navigating to the 'Change Password' option in your Profile."
                )
                FAQItem(
                    question = "How do I update my shipping address?",
                    answer = "You can update your shipping address by going to the 'Shipping Address' section in your Profile."
                )
                FAQItem(
                    question = "What are the supported payment methods?",
                    answer = "We accept all major credit cards, PayPal, and Apple Pay."
                )
                FAQItem(
                    question = "Can I save my favorite recipes?",
                    answer = "Yes, you can save recipes by clicking the 'Favorite' button on the recipe details page."
                )
                FAQItem(
                    question = "How do I contact support?",
                    answer = "You can contact our support team by navigating to the 'Contact Us' section in the app menu."
                )
            }
        }
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(horizontalArrangement = Arrangement.SpaceAround) {

                Text(
                    text = question,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,

                )

                Icon(
                    painter = painterResource(if (isExpanded) R.drawable.baseline_keyboard_arrow_up_24 else R.drawable.baseline_keyboard_arrow_down_24),
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }


            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = answer,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFAQPage() {
    FAQPage(navController = rememberNavController())
}
