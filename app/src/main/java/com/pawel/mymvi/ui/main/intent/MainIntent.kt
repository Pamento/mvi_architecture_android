package com.pawel.mymvi.ui.main.intent

sealed class MainIntent {
    object FetchUser: MainIntent()
    object FetchDogsFacts: MainIntent()
}
