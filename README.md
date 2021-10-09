![alt text](https://github.com/Singularity-Coder/Composables/blob/main/assets/banner_compose.png)
# Composables
List of Android's Jetpack Compose topics and their code snippets!

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

## Common Views
* Chat View
* Chat List Item
* Shopping Item
* Social Media Feed Item


## References
* https://foso.github.io/Jetpack-Compose-Playground/
* https://trello.com/c/zl06cBiQ/5-handle-fonts
* https://developer.android.com/jetpack/compose/tutorial
* https://developer.android.com/courses/pathways/compose
* https://compose.academy/
* https://stackoverflow.com/questions/59368360/how-to-use-compose-inside-fragment
