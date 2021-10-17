package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.theme.PlayFairFamily
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables

@Composable
fun ComposeTexts() {
    Board(title = Composables.TEXT.value) {
        // Link Text - change text color on click
        // Styling Text - Text Align, Baseline shift, Direction, Direction Algorithm, Geometric Transform, Indent, Overflow
        // Fonts - add custom fonts, font weights, font families, font style, font synthesis, ResourceFont, FontListFontFamily
        Text(text = stringResource(id = R.string.basic_text_view), modifier = Modifier.padding(top = 4.dp))
        Text(text = "Align Text to the end", textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue)) { append("M") }
                append("ultiple ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Red)) { append("S") }
                append("tyles")
            }
        )
        Text(text = "Justify this Text", textAlign = TextAlign.Justify, modifier = Modifier.fillMaxWidth()) // Not working
        Text(text = "Custom font", fontFamily = PlayFairFamily, fontWeight = FontWeight.Bold)
        Text(text = "Custom Text Size", fontSize = 8.sp)
        Text(text = "Colored Text View", color = Color.Magenta, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Thick Text", fontWeight = FontWeight.Black, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Thin Text", fontWeight = FontWeight.Thin, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Styled Text", fontStyle = FontStyle.Italic, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Monospace font style", fontFamily = FontFamily.Monospace, modifier = Modifier.padding(top = 4.dp))
        Text(text = "SanSerif font style", fontFamily = FontFamily.SansSerif, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Serif font style", fontFamily = FontFamily.Serif, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Cursive font style", fontFamily = FontFamily.Cursive, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Underlined Text", textDecoration = TextDecoration.Underline, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Strikeout Text", textDecoration = TextDecoration.LineThrough, modifier = Modifier.padding(top = 4.dp))
        Text(
            text = "Underline, Strikeout and Bold styles",
            style = TextStyle(textDecoration = TextDecoration.combine(listOf(TextDecoration.Underline, TextDecoration.LineThrough)), fontWeight = FontWeight.Bold)
        )
        Text(text = "Letter Spacing", letterSpacing = 8.sp, modifier = Modifier.padding(top = 4.dp))
        Surface(color = Color.Cyan) { Text(text = "Background Color", modifier = Modifier.padding(top = 4.dp)) }
        Text(text = "Overline", style = MaterialTheme.typography.overline, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Caption", style = MaterialTheme.typography.caption, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Subtitle2", style = MaterialTheme.typography.subtitle2, modifier = Modifier.padding(top = 4.dp))
        Text(text = "Subtitle1", style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(top = 4.dp))
        Text(text = "H6", style = MaterialTheme.typography.h6, modifier = Modifier.padding(top = 4.dp))
        Text(text = "H5", style = MaterialTheme.typography.h5, modifier = Modifier.padding(top = 4.dp))
        Text(text = "H4", style = MaterialTheme.typography.h4, modifier = Modifier.padding(top = 4.dp))
        Text(text = "H3", style = MaterialTheme.typography.h3, modifier = Modifier.padding(top = 4.dp))
        Text(text = "H2", style = MaterialTheme.typography.h2, modifier = Modifier.padding(top = 4.dp))
        Text(text = "H1", style = MaterialTheme.typography.h1, modifier = Modifier.padding(top = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeTextsPreview() = ComposablesApp { ComposeTexts() }