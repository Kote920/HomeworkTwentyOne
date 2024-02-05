package com.example.homeworktwentyone.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.domain.useCase.GetProductsUseCase
import com.example.homeworktwentyone.presentation.mapper.toPresentation
import com.example.homeworktwentyone.presentation.model.Category
import com.example.homeworktwentyone.presentation.model.ProductUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getProductsUseCase: GetProductsUseCase) :
    ViewModel() {


    private val _productUIListFlow = MutableSharedFlow<Resource<List<ProductUI>>>()
    val productUIListFlow: SharedFlow<Resource<List<ProductUI>>> = _productUIListFlow.asSharedFlow()


    private  var categories =  mutableSetOf(Category( "All", true))

    fun getProductList() {
        viewModelScope.launch {
            getProductsUseCase.invoke().collect() {

                when (it) {
                    is Resource.Loading -> _productUIListFlow.emit(Resource.Loading())
                    is Resource.Success -> {
                        for (each in it.responseData) {
                            categories.add(Category(each.category, false))
                        }
                        _productUIListFlow.emit(Resource.Success(it.responseData.map { product ->
                            product.toPresentation()
                        }))


                    }

                    is Resource.Failed -> _productUIListFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }

    fun getCategories(): List<Category> {
        return categories.toList()
    }


}