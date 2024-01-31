package com.example.homeworktwentyone.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.domain.useCase.GetClothesUseCase
import com.example.homeworktwentyone.presentation.mapper.toPresentation
import com.example.homeworktwentyone.presentation.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getClothesUseCase: GetClothesUseCase) :
    ViewModel() {


    private val _productListFlow = MutableSharedFlow<Resource<List<Product>>>()
    val productListFlow: SharedFlow<Resource<List<Product>>> = _productListFlow.asSharedFlow()

    fun getProductList() {
        viewModelScope.launch {
            getClothesUseCase.invoke().collect() {

                when (it) {
                    is Resource.Loading -> _productListFlow.emit(Resource.Loading())
                    is Resource.Success -> _productListFlow.emit(Resource.Success(it.responseData.map { product ->
                        product.toPresentation()
                    }))

                    is Resource.Failed -> _productListFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }


}