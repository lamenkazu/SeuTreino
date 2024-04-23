package com.example.seutreino.dagger_injection

import com.example.seutreino.model.repositories.firebase_repository.FirebaseAuthRepository
import com.example.seutreino.model.repositories.firebase_repository.FirebaseExercisesRepository
import com.example.seutreino.model.repositories.firebase_repository.FirebaseWorkoutRepository
import com.example.seutreino.model.repositories.interface_repository.IAuthRepository
import com.example.seutreino.model.repositories.interface_repository.IExercisesRepository
import com.example.seutreino.model.repositories.interface_repository.IWorkoutsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideExercisesRepository(
        database: FirebaseFirestore
    ): IExercisesRepository{
        return FirebaseExercisesRepository(database)
    }

    @Provides
    @Singleton
    fun provideWorkoutsRepository(
        database: FirebaseFirestore
    ): IWorkoutsRepository{
        return FirebaseWorkoutRepository(database)
    }

    @Provides
    @Singleton
    fun provideAuthsRepository(
        database: FirebaseFirestore,
        auth: FirebaseAuth
    ): IAuthRepository{
        return FirebaseAuthRepository(database, auth)
    }

}