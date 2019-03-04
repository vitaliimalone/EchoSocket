package com.vitaliimalone.echosocket.di

import com.vitaliimalone.echosocket.BuildConfig
import com.vitaliimalone.echosocket.data.repository.ChatRepository
import com.vitaliimalone.echosocket.data.repository.remote.ChatRemoteDataSource
import com.vitaliimalone.echosocket.domain.interactors.ChatInteractor
import com.vitaliimalone.echosocket.presentation.MainActivity
import com.vitaliimalone.echosocket.presentation.MainRouter
import com.vitaliimalone.echosocket.presentation.chat.ChatViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {
    // https://github.com/InsertKoinIO/koin/issues/49#issuecomment-414443350
    single { (activity: MainActivity) -> MainRouter(activity) }
    viewModel { ChatViewModel(get()) }
}
val domainModule = module {
    single { ChatInteractor(get()) }
}
val data = module {
    single { ChatRepository(get()) }
    single { ChatRemoteDataSource(get()) }
}
val networkModule = module {
    single {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        builder.build()
    }
}
val appModule = listOf(networkModule, data, domainModule, presentationModule)