package com.singularitycoder.testcomposestuff.ui.composewidgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.singularitycoder.testcomposestuff.R
import com.singularitycoder.testcomposestuff.ui.theme.ComposablesApp
import com.singularitycoder.testcomposestuff.ui.utils.Board
import com.singularitycoder.testcomposestuff.ui.utils.Composables
import com.singularitycoder.testcomposestuff.ui.utils.VerticalSpace

@Composable
fun ComposeImages() {
    Board(title = Composables.IMAGES.value) {
        Image(painter = painterResource(id = R.drawable.pan1), contentDescription = null)
        8.dp.VerticalSpace()
        Image(painter = painterResource(id = R.drawable.pan7), contentDescription = null, alpha = 0.2F, modifier = Modifier.fillMaxSize().height(80.dp), contentScale = ContentScale.Crop)
        8.dp.VerticalSpace()
        Image(painter = painterResource(id = R.drawable.pan2), contentDescription = null, modifier = Modifier.clip(RoundedCornerShape(16.dp)))
        8.dp.VerticalSpace()
        Image(painter = painterResource(id = R.drawable.pan3), contentDescription = null, modifier = Modifier.clip(CutCornerShape(16.dp)))
        8.dp.VerticalSpace()
        Image(painter = painterResource(id = R.drawable.pan5), contentDescription = null, modifier = Modifier.clip(CircleShape))
        8.dp.VerticalSpace()
        Image(painter = painterResource(id = R.drawable.pic1), contentDescription = null, modifier = Modifier.clip(CircleShape).height(64.dp).width(64.dp))
        8.dp.VerticalSpace()
        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Darken))
        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Lighten))
        Image(painter = painterResource(id = R.drawable.pan4), contentDescription = null, colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Overlay))
        8.dp.VerticalSpace()
        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null)
        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null, colorFilter = ColorFilter.lighting(Color.Yellow, Color.Blue))
        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null, colorFilter = ColorFilter.lighting(Color.Green, Color.Red))
        Image(painter = painterResource(id = R.drawable.pan6), contentDescription = null, colorFilter = ColorFilter.lighting(Color.Green, Color.Black))
        8.dp.VerticalSpace()

        // https://coil-kt.github.io/coil/compose/
        Image(
            painter = rememberImagePainter(
                data = "https://cdn.pixabay.com/photo/2020/05/12/11/39/cat-5162540_1280.jpg",
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_background)
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeImagesPreview() = ComposablesApp { ComposeImages() }