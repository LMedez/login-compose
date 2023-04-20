package com.luc.login.presentation.di

import com.luc.login.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { UserViewModel(get()) }
}