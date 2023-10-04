package com.example.refactoringlife4.ui.randomDog.viewModel


open class RandomDogViewModelEvent {

    data class ShowSuccessView(val image:String) : RandomDogViewModelEvent()

    object ShowError: RandomDogViewModelEvent()
}
