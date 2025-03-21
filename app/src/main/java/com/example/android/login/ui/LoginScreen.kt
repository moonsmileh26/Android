package com.example.android.login.ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.R
import com.example.android.login.viewmodel.LoginViewModel
import com.example.android.quotes.view.MainActivity
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(modifier = Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        LoginView(modifier, uiState, viewModel)
    }
}

@Composable
fun LoginView(modifier: Modifier, uiState: LoginState, viewModel: LoginViewModel) {
    val loginViewState by viewModel.loginViewState.collectAsState()


    Column(modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        EmailField(
            loginViewState.email,
            onValueChange = { viewModel.onLoginChanged(it, loginViewState.password) })
        Spacer(modifier = Modifier.size(8.dp))
        PasswordField(
            loginViewState.password,
            onValueChange = { viewModel.onLoginChanged(loginViewState.email, it) })
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(8.dp))

        val coroutineScope = rememberCoroutineScope()

        LoginButton(loginViewState.loginEnable, {
            coroutineScope.launch {
                viewModel.onDoLogin()
            }
        })
    }

    val context = LocalContext.current
    val activity = (context as? MainActivity)

    if (uiState.isLoginSuccess) {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        Intent(context, MainActivity::class.java).also {
            context.startActivity(it)
        }
        activity?.finish()
    } else if (uiState.isLoginError) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
            contentColor = Color.White,
            disabledContentColor = Color.Gray,
        ), enabled = loginEnable
    ) {
        Text(text = "Login")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot your password?",
        modifier = modifier.clickable { },
        color = Color.Blue,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Logo",
        modifier = modifier
    )
}

@Composable
fun EmailField(email: String, onValueChange: (String) -> Unit) {
    TextField(
        value = email, onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}

@Composable
fun PasswordField(password: String, onValueChange: (String) -> Unit) {
    TextField(
        value = password, onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}