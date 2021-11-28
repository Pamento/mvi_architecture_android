package com.pawel.mymvi.ui.main.viewState

import com.pawel.mymvi.data.model.User

sealed class  MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Users(val user: List<User>): MainState()
    data class DogsFacts(val dogsFacts: com.pawel.mymvi.data.model.DogsFacts): MainState()
    data class Error(val error: String?): MainState()
}
