package com.divinkas.app.words.base.installer

import com.divinkas.app.words.base.AppInstance
import com.divinkas.app.words.helper.navigation.Navigator
import com.divinkas.app.words.helper.navigation.NavigatorImpl
import com.divinkas.app.words.modules.LocalDataModule
import com.divinkas.app.words.modules.NetworkModule
import com.divinkas.app.words.room.EngWordsDB
import com.divinkas.app.words.room.category.CategoryRepository
import com.divinkas.app.words.room.category.CategoryRepositoryImpl
import com.divinkas.app.words.room.word.WordRepository
import com.divinkas.app.words.room.word.WordRepositoryImpl
import com.divinkas.app.words.ui.category.CategoryViewModel
import com.divinkas.app.words.ui.main.HomeViewModel
import com.divinkas.app.words.ui.setting.SettingViewModel
import com.divinkas.app.words.ui.test.TestViewModel
import com.divinkas.app.words.ui.word.WordViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

object KoinInstaller : ApplicationInstaller {
    private val appModule = module {
        single { androidApplication() as AppInstance }
        single<Navigator> { NavigatorImpl(get()) }
        single { LocalDataModule(get()) }
        single { NetworkModule(get()) }
        single { EngWordsDB.getDatabase(get())}
    }

    private val roomRepositories = module {
        factory<WordRepository> {
            WordRepositoryImpl(
                get()
            )
        }
        factory<CategoryRepository> {
            CategoryRepositoryImpl(
                get()
            )
        }
    }

    private val apiModules = module {
    }

    private val viewModule = module {
        viewModel { HomeViewModel() }
        viewModel { SettingViewModel() }
        viewModel { WordViewModel() }
        viewModel { CategoryViewModel() }
        viewModel { TestViewModel() }
    }

    override fun install(appInstance: AppInstance) {
        startKoin {
            androidLogger()
            androidContext(appInstance)
            modules(appModule)
            modules(roomRepositories)
            modules(apiModules)
            modules(viewModule)
        }
    }
}