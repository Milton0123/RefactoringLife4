package com.example.refactoringlife4.ui.searchDog.viewModel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.refactoringlife4.model.usesCases.OneDogUseCase
import com.example.refactoringlife4.ui.searchDog.viewModel.SearchViewModelEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchOneDogViewModel(
    private val context: Context, private val oneDogSearch: OneDogUseCase= OneDogUseCase(context=context)
) : ViewModel() {

    private val _data = MutableLiveData<SearchViewModelEvent>()
    val data: LiveData<SearchViewModelEvent> = _data

    fun getOneDog(breed:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val useCaseSearch= oneDogSearch.invoke(breed)

            if(useCaseSearch.isSuccess){
                _data.postValue(SearchViewModelEvent.ShowSuccessView(useCaseSearch.image))
            }else{
                _data.postValue(SearchViewModelEvent.ShowError)
            }
            }

        }
}