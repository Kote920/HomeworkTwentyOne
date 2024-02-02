package com.example.homeworktwentyone.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.domain.useCase.GetClothesUseCase
import com.example.homeworktwentyone.presentation.mapper.toPresentation
import com.example.homeworktwentyone.presentation.model.ProductUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getClothesUseCase: GetClothesUseCase) :
    ViewModel() {


    private val _productUIListFlow = MutableSharedFlow<Resource<List<ProductUI>>>()
    val productUIListFlow: SharedFlow<Resource<List<ProductUI>>> = _productUIListFlow.asSharedFlow()

    fun getProductList() {
        viewModelScope.launch {
            getClothesUseCase.invoke().collect() {

                when (it) {
                    is Resource.Loading -> _productUIListFlow.emit(Resource.Loading())
                    is Resource.Success -> _productUIListFlow.emit(Resource.Success(it.responseData.map { product ->
                        product.toPresentation()
                    }))

                    is Resource.Failed -> _productUIListFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }


}