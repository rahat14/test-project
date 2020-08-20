package dev.syntext_error.testproject.views.Activities

import android.content.Intent

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.syntext_error.testproject.R
import dev.syntext_error.testproject.models.QustionModel
import dev.syntext_error.testproject.models.QustionShowModel
import dev.syntext_error.testproject.models.QustionShowModelImpl
import dev.syntext_error.testproject.utils.Utilities
import dev.syntext_error.testproject.viewmodel.GetQustionViewModel
import kotlinx.android.synthetic.main.activity_qustion_landing_page.*
import kotlinx.android.synthetic.main.fragment_mcq.*

class qustionLandingPage : AppCompatActivity() {

    private lateinit var model: QustionShowModel
    private lateinit var viewModel: GetQustionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qustion_landing_page)
        // initialize model. (I know we should not initialize model in View. But for simplicity...)
        model = QustionShowModelImpl(applicationContext)
        // initialize ViewModel
        viewModel = ViewModelProvider(this).get(GetQustionViewModel::class.java)

        setLiveDataListeners()
        viewModel.getQusitonList(model)

        startBtn.setOnClickListener {
            val intent = Intent(applicationContext , qustionHostPage::class.java)
            startActivity(intent)
        }
    }

    private fun setLiveDataListeners() {



        viewModel.progressBarLiveData.observe(this , Observer { isShowLoader ->

            if(isShowLoader) {
                progressBar.visibility = View.VISIBLE
                startBtn.visibility = View.GONE

            }
            else {
                progressBar.visibility = View.GONE
                startBtn.visibility = View.VISIBLE
            }


        })

        viewModel.QustionListLiveData.observe(this  , object : Observer<List<QustionModel>> {
            override fun onChanged(t: List<QustionModel>) {

                // you Have the data
                // saving  the data in shared Pref
              //  Toast.makeText(applicationContext , "Data Downloaded" , Toast.LENGTH_SHORT).show()
                Utilities.saveData(applicationContext , t)

            }
        })




//        viewModel.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
//            output_group.visibility = View.GONE
//            tv_error_message.visibility = View.VISIBLE
//            tv_error_message.text = errorMessage
//        })
    }
}