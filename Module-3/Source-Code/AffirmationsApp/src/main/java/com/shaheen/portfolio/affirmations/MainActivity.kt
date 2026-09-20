package com.shaheen.portfolio.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Affirmation(
    val title: String,
    val detail: String
)

private val affirmations = listOf(
    Affirmation("Keep learning", "Every small concept you understand becomes part of a stronger foundation."),
    Affirmation("Build, then improve", "A working first version gives you something concrete to test and refine."),
    Affirmation("Read the error", "Compiler and runtime messages are clues, not just obstacles."),
    Affirmation("Practice deliberately", "Repeat the exact skill you want to improve instead of only rereading notes."),
    Affirmation("Use state carefully", "Keep UI state clear so the screen stays predictable as data changes."),
    Affirmation("Design for people", "Readable text, useful spacing, and accessibility make software better for everyone."),
    Affirmation("Test assumptions", "A quick test can replace a long guess about how code behaves."),
    Affirmation("Prefer clear code", "Readable names and small functions reduce future debugging time."),
    Affirmation("Commit meaningful progress", "Version control is most useful when commits explain how the project evolved."),
    Affirmation("Reflect after building", "Documenting what worked and what failed turns practice into reusable knowledge.")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AffirmationsScreen()
                }
            }
        }
    }
}

@Composable
fun AffirmationsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Developer Affirmations",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(affirmations) { index, affirmation ->
                AffirmationCard(index + 1, affirmation)
            }
        }
    }
}

@Composable
private fun AffirmationCard(number: Int, affirmation: Affirmation) {
    var expanded by rememberSaveable(affirmation.title) { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .animateContentSize()
            .semantics {
                contentDescription = "Affirmation $number: ${affirmation.title}. Tap to ${if (expanded) "collapse" else "expand"}."
            }
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "Day $number",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = affirmation.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = affirmation.detail,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AffirmationsPreview() {
    MaterialTheme {
        AffirmationsScreen()
    }
}
