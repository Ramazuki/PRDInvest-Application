package com.example.myjetpackfront.ui.stocks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myjetpackfront.MyApplication
import com.example.myjetpackfront.data.user.User
import com.example.myjetpackfront.data.user.UsersRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ProfileUiState {
    data class Success(val getUserInfo: User): ProfileUiState
    object Error: ProfileUiState
    object Loading: ProfileUiState
}

class ProfileViewModel(
    private val usersRepository: UsersRepository
): ViewModel(){

    var profileUiState: ProfileUiState by mutableStateOf(ProfileUiState.Loading)
        private set

    private val _profileWidgetState: MutableState<ProfileWidgetState> =
        mutableStateOf(value = ProfileWidgetState.CLOSED)
    val profileWidgetState: State<ProfileWidgetState> = _profileWidgetState

    fun updateUserWidgetState(newValue: ProfileWidgetState) {
        _profileWidgetState.value = newValue
    }
    init {
        getProfileInfo()
    }

    fun getProfileInfo(userId: Int = 1) {
        viewModelScope.launch {
            profileUiState = ProfileUiState.Loading
            profileUiState = try {
                ProfileUiState.Success(usersRepository.getUserInfo(userId))
            } catch (e: IOException){
                ProfileUiState.Error
            } catch (e: HttpException){
                ProfileUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApplication = (this[APPLICATION_KEY] as MyApplication)
                val usersRepository = myApplication.container.usersRepository
                ProfileViewModel(usersRepository = usersRepository)
            }
        }
    }
}

enum class ProfileWidgetState {
    OPENED,
    CLOSED
}
