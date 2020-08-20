package dev.syntext_error.testproject.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import dev.syntext_error.testproject.R
import dev.syntext_error.testproject.views.MainActivity
import kotlinx.android.synthetic.main.fragment_done_fragment.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [done_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class done_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

// This callback will only be called when MyFragment is at least Started.

        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_done_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gotToDashBoard.setOnClickListener {

            val inp = Intent(requireContext(), MainActivity::class.java)
            startActivity(inp)
            requireActivity().finish()

          //  Toast.makeText(requireContext() , "You Cant Go Back !!" , Toast.LENGTH_SHORT).show()
            requireActivity().onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        // if you want onBackPressed() to be called as normal afterwards
                        Toast.makeText(requireContext() , "You Cant Go Back !!" , Toast.LENGTH_SHORT).show()

                    }
                }
            )

        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment done_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            done_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}