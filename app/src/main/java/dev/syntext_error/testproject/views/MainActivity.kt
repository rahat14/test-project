package dev.syntext_error.testproject.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.syntext_error.testproject.R
import dev.syntext_error.testproject.adapter.ResponseAdapter
import dev.syntext_error.testproject.models.QustionModel
import dev.syntext_error.testproject.models.AnswersModel
import dev.syntext_error.testproject.viewmodel.ResponsesViewModel
import dev.syntext_error.testproject.views.Activities.qustionLandingPage
import dev.syntext_error.testproject.views.Activities.singleResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , ResponseAdapter.CellClickListener {



    private var responseViewModel: ResponsesViewModel? = null

    lateinit var adapter:ResponseAdapter
    private var cityList: List<QustionModel> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        responseViewModel = ViewModelProvider(this).get(ResponsesViewModel::class.java)

        responseViewModel?.getMessages()?.observe(this, { this.renderResponses(it) })

        addBtn.setOnClickListener {
            val intent : Intent  = Intent(applicationContext , qustionLandingPage::class.java)
            startActivity(intent)
        }

    }

    private fun renderResponses(messages: List<AnswersModel>?){
        adapter = ResponseAdapter(this,messages, this )
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        layoutManager.reverseLayout = true
        respList.layoutManager = layoutManager
        respList.adapter = adapter
    }

    override fun onCellClickListener(data : AnswersModel?) {

        val intent = Intent(applicationContext, singleResponse::class.java)
        intent.putExtra("resp", data)
        startActivity(intent)

    }


}