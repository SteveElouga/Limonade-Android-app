package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Lemonade()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun Lemonade() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        MakeLemonade(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun MakeLemonade(modifier: Modifier = Modifier) {
    var currentStep by remember {
        mutableIntStateOf(1)
    }
    var squeezeCount by remember {
        mutableIntStateOf(0)
    }
    when (currentStep) {
        1 -> LemonTextAndImage(
            textId = R.string.step_1,
            imageId = R.drawable.lemon_tree,
            imageTextId = R.string.lemon_tree,
            onImageClick = {
                currentStep++
                squeezeCount = (2..4).random()
            })

        2 -> LemonTextAndImage(
            textId = R.string.step_2,
            imageId = R.drawable.lemon_squeeze,
            imageTextId = R.string.lemon,
            onImageClick = {
                squeezeCount--
                if (squeezeCount == 0) currentStep++
            })

        3 -> LemonTextAndImage(
            textId = R.string.step_3,
            imageId = R.drawable.lemon_drink,
            imageTextId = R.string.elass_of_lemonade,
            onImageClick = { currentStep++ })

        4 -> LemonTextAndImage(
            textId = R.string.step_4,
            imageId = R.drawable.lemon_restart,
            imageTextId = R.string.empty_glass,
            onImageClick = { if (currentStep>3) currentStep = 1 })
    }

}

@Composable
fun LemonTextAndImage(
    textId: Int,
    imageId: Int,
    imageTextId: Int,
    modifier: Modifier = Modifier,
    onImageClick: () -> Unit
) {
    val image = painterResource(id = imageId)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
        ) {
            Image(
                painter = image,
                contentDescription = stringResource(id = imageTextId),
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = textId),
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}