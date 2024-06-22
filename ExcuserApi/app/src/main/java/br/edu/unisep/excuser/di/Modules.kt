package br.edu.unisep.excuser.di

import br.edu.unisep.excuser.data.service.factory.ServiceFactory
import br.edu.unisep.excuser.domain.mapper.ExcuseMapper
import br.edu.unisep.excuser.domain.repository.excuse.ExcuseRepository
import br.edu.unisep.excuser.ui.excuse.viewmodel.ExcuseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    single { ServiceFactory.getExcuseService() }
}

val mapperModule = module {
    single { ExcuseMapper() }
}

val repositoryModule = module {
    single { ExcuseRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { ExcuseViewModel(get()) }
}