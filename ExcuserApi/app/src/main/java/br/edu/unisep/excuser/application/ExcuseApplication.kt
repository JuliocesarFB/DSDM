package br.edu.unisep.excuser.application

import android.app.Application
import br.edu.unisep.excuser.di.mapperModule
import br.edu.unisep.excuser.di.repositoryModule
import br.edu.unisep.excuser.di.serviceModule
import br.edu.unisep.excuser.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ExcuseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDI()
    }

    private fun initializeDI() {
        startKoin {
            androidContext(applicationContext)
            androidLogger()
            modules(
                serviceModule,
                mapperModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}