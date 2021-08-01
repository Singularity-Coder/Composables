![alt text](https://github.com/Singularity-Coder/Composables/blob/main/assets/banner_compose.png)
# Composables
List of Android's Jetpack Compose topics and their code snippets!

## TextView
```Kotlin
@Composable
fun ComposeTextView() {
    Text(
        text = "Hithesh",
        color = Color.Green,
        fontSize = TextUnit.Unspecified
    )
}
```

## EditText
```Kotlin
@Composable
fun ComposeEditText() {
    TextField(
        value = "My Text Field",
        onValueChange = {}
    )
}
```

## Button
```Kotlin
@Composable
fun ComposeButton() {
    Button(onClick = { /*TODO*/ }) {

    }
}
```

## ImageView
```Kotlin
@Composable
fun ComposeImageView() {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "My Launcher Image",
        modifier = Modifier
            .height(144.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}
```

## Vertical Linear Layout
```Kotlin
@Composable
fun ComposeVerticalLinearLayout() {
    Column(modifier = Modifier.fillMaxSize()) {
        // View 1
        // View 2
    }
}
```

## Horizontal Linear Layout
```Kotlin
@Composable
fun ComposeHorizontalLinearLayout() {
    Row(modifier = Modifier.fillMaxSize()) {
    	// View 1
    	// View 2
    }
}
```

## Coordinator Layout
```Kotlin
@Composable
fun ComposeCoordinatorLayout() {
    // Stack elements
    Box(modifier = Modifier.fillMaxSize()) {

    }
}
```

## Vertical List View
```Kotlin
@Composable
fun ComposeVerticalListView() {
    // RecyclerView
    LazyColumn {
        itemsIndexed(listOf(1, 2, 3, 4, 5)) { index, item ->
            Text(text = item.toString())
        }
    }
}
```

## Horizontal List View
```Kotlin
@Composable
fun ComposeHorizontalListView() {
    // RecyclerView
    LazyRow {
        itemsIndexed(listOf(1, 2, 3, 4, 5)) { index, item ->
            Text(text = item.toString())
        }
    }
}
```

## Change View Background
```Kotlin
@Composable
fun ComposeChangeViewBackground() {
    Surface(color = Color.Green, modifier = Modifier.padding(all = 16.dp)) {
        // Changes the View's background color to green and gives itself a padding of 16dp
    }
}
```

## Add Horoizontal Gap between Views
```Kotlin
@Composable
fun ComposeAddHorizontalGapBtwViews() {
    Row {
        // View 1
        Spacer(modifier = Modifier.width(8.dp))
        // View 2
    }
}
```

## Add Vertical Gap between Views
```Kotlin
@Composable
fun ComposeAddVerticalGapBtwViews() {
    Column {
        // View 1
        Spacer(modifier = Modifier.height(8.dp))
        // View 2
    }
}
```

## Fragment
* https://stackoverflow.com/questions/59368360/how-to-use-compose-inside-fragment

## Common Views
* Chat View
* Chat List Item
* Shopping Item
* Social Media Feed Item


## References
* https://developer.android.com/jetpack/compose/tutorial
* https://developer.android.com/courses/pathways/compose
