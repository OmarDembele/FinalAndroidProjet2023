package com.esmt.cours.finalandroidprojet2023.presentation.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esmt.cours.finalandroidprojet2023.domain.repository.AuthRepository
import kotlinx.coroutines.launch


class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {
    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(userName: String) {
        loginUiState = loginUiState.copy(userName = userName)
    }
    fun onPasswordNameChange(password: String) {
        loginUiState = loginUiState.copy(password = password)
    }

    fun onUserNameChangeSignUp(userName: String) {
        loginUiState = loginUiState.copy(userNameSignUp = userName)
    }

    fun onPasswordNameChangeSignUp(password: String) {
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }

    fun onConfirmPasswordNameChangeSignUp(password: String) {
        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
    }

    private  fun validateLoginForm() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.password.isNotBlank()

    private fun validateSignUpForm() =
        loginUiState.userNameSignUp.isNotBlank() &&
            loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmPasswordSignUp.isNotBlank()


    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if(!validateSignUpForm()){
                throw IllegalArgumentException("Email and Password can not be empty")
        }
            loginUiState = loginUiState.copy(isLoading = true)
            if(loginUiState.passwordSignUp != loginUiState.confirmPasswordSignUp){
                throw IllegalArgumentException("Password do not match")
            }
            loginUiState = loginUiState.copy(signUpError = null)
            repository.createUser(
                loginUiState.userNameSignUp,
                loginUiState.passwordSignUp
            ){
                isSuccessFul ->
                loginUiState = if(isSuccessFul){
                    Toast.makeText(context, "Success Login", Toast.LENGTH_SHORT).show()
                    loginUiState.copy(isSuccessFul = true)
                } else{
                    Toast.makeText(context, "Failed Login", Toast.LENGTH_SHORT).show()
                    loginUiState.copy(isSuccessFul = false)
                }
            }
    }catch (e:java.lang.Exception){
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
        e.printStackTrace()
    }finally {
            loginUiState = loginUiState.copy(isLoading = false)
    }


}

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if(!validateLoginForm()){
                throw IllegalArgumentException("Email and Password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.userName,
                loginUiState.password
            ){
                    isSuccessFul ->
                loginUiState = if(isSuccessFul){
                    Toast.makeText(context, "Success Login", Toast.LENGTH_SHORT).show()
                    loginUiState.copy(isSuccessFul = true)
                } else{
                    Toast.makeText(context, "Failed Login", Toast.LENGTH_SHORT).show()
                    loginUiState.copy(isSuccessFul = false)

                }
            }
        }
        catch (e:java.lang.Exception){
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        }
        finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }



    data class LoginUiState(
        val userName: String = "",
        val password: String = "",
        val userNameSignUp: String = "",
        val passwordSignUp: String = "",
        val confirmPasswordSignUp: String="",
        val isLoading: Boolean = false,
        val isSuccessFul:Boolean = false,
        val signUpError: String? = null,
        val loginError: String? = null
    )
}














/* private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signupFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Resource<FirebaseUser>?> = _signupFlow

    val currentUser: FirebaseUser?
        get() = repository.currentUser

    init {
        if (repository.currentUser != null) {
            _loginFlow.value = Resource.Success(repository.currentUser!!)
        }
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val result = repository.login(email, password)
        _loginFlow.value = result
    }

    fun signupUser(name: String, email: String, password: String) = viewModelScope.launch {
        _signupFlow.value = Resource.Loading
        val result = repository.signup(name, email, password)
        _signupFlow.value = result
    }

    fun logout() {
        repository.logout()
        _loginFlow.value = null
        _signupFlow.value = null
    }
}*/