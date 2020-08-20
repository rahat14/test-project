package dev.syntext_error.testproject.views.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import dev.syntext_error.testproject.R

class qustionHostPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qustion_host_page)

        val host = NavHostFragment.create(R.navigation.qustion_navigation)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, host).setPrimaryNavigationFragment(host).commit()

//       val qusList : List<QustionModel> =  Utilities.fetchArrayList(applicationContext)
//
//        for(item in qusList)
//        {
//           Log.d("TAGA" , " " + item.question)
//        }

    }
}