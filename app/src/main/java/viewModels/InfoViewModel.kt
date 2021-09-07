package viewModels

import android.graphics.Bitmap
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api.ApiClient
import data.BookingHistortyResponse
import data.ResponseClient
import database.UserProfileDao
import event.Event
import interfaces.ApiInterface
import io.reactivex.Single
import kotlinx.coroutines.launch


class InfoViewModel:ViewModel() {
    var firstName= ObservableField<String>()
    var lastName= ObservableField<String>()
    var email= ObservableField<String>()
    var mobile= ObservableField<String>()
    var imageSrc=MutableLiveData<Bitmap>()
    var callError=MutableLiveData<Event<String>>()
    lateinit var paymentInfo: ResponseClient
    lateinit var historyInfo: Single<BookingHistortyResponse>
    var allinfo= MutableLiveData<Event<String>>()



    fun getPaymentInfo(token:String)
    {
        viewModelScope.launch {
            val req= ApiClient.callService(ApiInterface::class.java)
            try {
                paymentInfo=req.getDataGet(token)
                callError.value=Event("Fetch Success")
            }
            catch (E: Exception)
            {
                callError.value=Event("Fetch Failed")
            }
        }
    }

    fun getHistorytInfo(token:String)
    {
        viewModelScope.launch {
            val req= ApiClient.callService2(ApiInterface::class.java)
            try {
                historyInfo=req.getDataPssengerDetails(token)
                callError.value=Event("Fetch History Success")
            }
            catch (E: Exception)
            {
                callError.value=Event("Fetch Failed")
            }
        }
    }

    fun getAllInfoFromDB(uname: String,dao: UserProfileDao){
        viewModelScope.launch {
            allinfo.value= Event(dao.findByUname(uname))
        }
    }

}