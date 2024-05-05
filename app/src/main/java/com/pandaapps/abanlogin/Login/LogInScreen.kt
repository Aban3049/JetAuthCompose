package aban.com.AbanLogin.ui.theme.Login


import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandaapps.abanlogin.R
import com.pandaapps.abanlogin.components.HeaderText
import com.pandaapps.abanlogin.components.LogInTextField
import com.pandaapps.abanlogin.ui.theme.AbanLoginTheme


val defaultPadding = 16.dp
val itemSpacing = 8.dp


@Composable

fun LogInScreen(onLogInClick: () -> Unit, onSignUpClick: () -> Unit) {

    val context = LocalContext.current

    val (userName, setUserName) = rememberSaveable {
        mutableStateOf("")
    }

    val (userPassword, setPassword) = rememberSaveable {
        mutableStateOf("")
    }

    val (checked, onCheckedChange) = rememberSaveable {
        mutableStateOf(false)
    }

    val isFieldEmpty = userName.isNotEmpty() && userPassword.isNotEmpty()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        HeaderText(
            text = "Login",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(Alignment.Start)
                .weight(1f)


        )




        LogInTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = setUserName,
            labelText = "Username",
            leadingIcon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        LogInTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userPassword,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(itemSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = onCheckedChange)
                Text(text = "Remember me", color = Color.White)
            }
            TextButton(onClick = { }) {
                Text(text = "Forgot Password")
            }
        }

        Spacer(modifier = Modifier.height(itemSpacing))

        Button(
            onClick = {
                onLogInClick()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldEmpty

            ) {
            Text(text = "Login")
        }


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                // Rest of the code remains the same
                AlternativeLoginOptions(
                    onIconClick = { index ->
                        when (index) {
                            0 -> {
                                Toast.makeText(
                                    context,
                                    "Logged in with Instagram",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            1 -> {
                                Toast.makeText(context, "Logged in with Github", Toast.LENGTH_SHORT)
                                    .show()

                            }

                            2 -> {
                                Toast.makeText(context, "Logged in with Google", Toast.LENGTH_SHORT)
                                    .show()

                            }
                        }

                    },
                    onSignUpClick = {
                        onSignUpClick()
                    },
                    modifier = Modifier.wrapContentSize(align = Alignment.BottomCenter)
                )
            }
        }


    }

}


@Composable
fun AlternativeLoginOptions(
    onIconClick: (index: Int) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val iconList = listOf(
        R.drawable.icon_instagram,
        R.drawable.icon_github,
        R.drawable.icon_google
    )



    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text("or Sign in With", color = Color.White)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            iconList.forEachIndexed { index, iconResId ->
                Icon(
                    painter = painterResource(id = iconResId),
                    contentDescription = "alternativeLogIn",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            onIconClick(index)
                        }
                        .clip(CircleShape),
                    tint = Color.White
                )


                Spacer(modifier = modifier.width(defaultPadding))
            }


        }



        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account?", color = Color.White)
            TextButton(onClick = onSignUpClick) {
                Text(text = "Sign Up")
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable

fun PreviewLogInScreen() {

    AbanLoginTheme {
        LogInScreen({}, {})
    }
}

