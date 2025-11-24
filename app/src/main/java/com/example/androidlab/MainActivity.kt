package com.example.androidlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottoApp()
        }
    }
}

// 앱 전체를 감싸는 Composable
@Composable
fun LottoApp() {
    val navController = rememberNavController()

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LottoNavHost(navController = navController)
        }
    }
}

// 두 화면을 관리하는 NavHost
@Composable
fun LottoNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onStartClick = { navController.navigate("result") }
            )
        }
        composable("result") {
            ResultScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

// ----------- 메인 화면 -----------
@Composable
fun MainScreen(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier.run {
            fillMaxSize()
                .padding(24.dp)
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lotto 번호 추출기",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.lotto_icon ),
            contentDescription = "로또 아이콘",
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("번호 추출", fontSize = 18.sp)
        }
    }
}

// 1~45에서 랜덤 6개 당첨번호 뽑는 함수
fun generateLottoNumbers(): List<Int> =
    (1..45).shuffled().take(6).sorted()


// ----------- 결과 화면 -----------
@Composable
fun ResultScreen(onBackClick: () -> Unit) {
    // 화면에 현재까지 보여줄 숫자 리스트
    var shownNumbers by remember { mutableStateOf<List<Int>>(emptyList()) }

    // 다시추출을 위한 카운트 키
    var drawCount by remember {mutableStateOf(0)}

    // drawCount가 변경될때 마다 추출 다시 실행
    LaunchedEffect(drawCount) {
        shownNumbers = emptyList() // 화면의 숫자들 초기화하기

        val all = generateLottoNumbers()
        val temp = mutableListOf<Int>()

        for(n in all){
            temp.add(n)
            shownNumbers = temp.toList()
            delay(500L)
        }
    }

    // 한번 들어오면 자동으로 번호를 천천히 보여주는 로직
    LaunchedEffect(Unit) {
        val all = generateLottoNumbers()
        val temp = mutableListOf<Int>()
        for (n in all) {
            temp.add(n)
            shownNumbers = temp.toList()
            delay(500L) // 0.5초마다 하나씩
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "추출된 번호",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 숫자들을 가로로 보여주기
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            shownNumbers.forEach { num ->
                NumberBall(num = num)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(onClick = {
                // drawCount 를 증가시켜서 재추출
                drawCount++
            }) {
                Text("다시 추출")
            }

            OutlinedButton(onClick = onBackClick) {
                Text("뒤로가기")
            }
        }
    }
}
// 숫자 공 하나를 표현하는 작은 UI
@Composable
fun NumberBall(num: Int) {
    Surface(
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = num.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
