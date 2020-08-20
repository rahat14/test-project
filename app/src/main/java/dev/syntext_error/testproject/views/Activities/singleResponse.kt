package dev.syntext_error.testproject.views.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.syntext_error.testproject.R
import dev.syntext_error.testproject.adapter.ansAdapter
import dev.syntext_error.testproject.models.AnswersModel
import dev.syntext_error.testproject.models.QustionModel
import kotlinx.android.synthetic.main.activity_single_response.*


private var qusList: List<QustionModel>? = null
class singleResponse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_response)
        val response = intent.getSerializableExtra("resp") as? AnswersModel

        titleHeader.text = titleHeader.text.toString() + response?.id.toString()

            // make a list out of it
        val list = arrayListOf<String>()
        list.add(response?.checkbox.toString())
        list.add(response?.dropdown.toString())
        list.add(response?.mcq.toString())
        list.add(response?.text.toString())
        list.add(response?.number.toString())

        // now passing it to the adapter

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ansAdapter(this, list)
    }
}