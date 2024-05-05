package com.pandaapps.abanlogin.SignUp

import aban.com.AbanLogin.ui.theme.Login.defaultPadding
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandaapps.abanlogin.components.HeaderText
import com.pandaapps.abanlogin.components.LogInTextField
import com.pandaapps.abanlogin.ui.theme.AbanLoginTheme

@Composable

fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit
) {

    val (firstName, onFirstNameChange) = rememberSaveable {
        mutableStateOf("")
    }

    val (lastName, onLastNameChange) = rememberSaveable {
        mutableStateOf("")
    }

    val (email, onEmailChange) = rememberSaveable {
        mutableStateOf("")
    }

    val (password, onPasswordChange) = rememberSaveable {
        mutableStateOf("")
    }

    val (confirmPassword, onConfirmPassword) = rememberSaveable {
        mutableStateOf("")
    }

    val (agree, onAgreeChange) = rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var isPasswordSame by remember {
        mutableStateOf(false)
    }

    val isFieldNotEmpty =
        firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && agree

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        AnimatedVisibility(isPasswordSame) {
            Text("Password Mismatch", color = Color.White)
        }

        HeaderText(
            text = "Sign Up",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(Alignment.Start)
                .weight(1f)

        )

        LogInTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstName,
            onValueChange = onFirstNameChange,
            labelText = "First Name",
            leadingIcon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(defaultPadding))

        LogInTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            onValueChange = onLastNameChange,
            labelText = "Last Name",
            leadingIcon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(defaultPadding))

        LogInTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = onEmailChange,
            labelText = "Email",
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email

        )

        Spacer(modifier = Modifier.height(defaultPadding))

        LogInTextField(
            modifier = Modifier.fillMaxSize(),
            value = password,
            onValueChange = onPasswordChange,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,

            )

        Spacer(modifier = Modifier.height(defaultPadding))

        LogInTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPassword,
            onValueChange = onConfirmPassword,
            labelText = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,


            )

        Spacer(modifier = Modifier.height(defaultPadding))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            val privacyText = "Privacy Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("I Agree with")

                }

                append(" ")

                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = privacyText, privacyText)
                    append(privacyText)
                }
            }

            Checkbox(checked = agree, onCheckedChange = onAgreeChange)
            ClickableText(annotatedString) { offset ->
                annotatedString.getStringAnnotations(offset, offset).forEach {
                    when (it.tag) {
                        privacyText -> {
                            Toast.makeText(context, "Privacy Text Clicked", Toast.LENGTH_SHORT)
                                .show()
                            onPrivacyPolicyClick()
                        }
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(defaultPadding + 8.dp))
        Button(modifier = Modifier.fillMaxWidth(), enabled = isFieldNotEmpty, onClick = {
            isPasswordSame = password != confirmPassword
            if (!isPasswordSame) {
                onSignUpClick()
            }
        }) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(defaultPadding))

        val signInText = "Sign In"
        val signInAnnotatedString = buildAnnotatedString {

            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                append("Already have an account?")
            }

            append("  ")

            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                pushStringAnnotation(signInText, signInText)
                append(signInText)
            }


        }

        ClickableText(signInAnnotatedString) { offset ->
            signInAnnotatedString.getStringAnnotations(offset, offset).forEach {
                if (it.tag == signInText) {
                    Toast.makeText(context, "Sign in Clicked", Toast.LENGTH_SHORT).show()
                    onLoginClick()
                }
            }

        }

    }


}


@Preview(showSystemUi = true)
@Composable

fun PreviewSignUp() {
    AbanLoginTheme {
        SignUpScreen({}, {}, {})
    }
}