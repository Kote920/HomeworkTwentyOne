package com.example.homeworktwentyone.domain.useCase

import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import javax.inject.Inject

class GetClothesUseCase @Inject constructor(private val repository: GetClothesRepository){

        suspend operator fun invoke() = repository.getClothesList()


}