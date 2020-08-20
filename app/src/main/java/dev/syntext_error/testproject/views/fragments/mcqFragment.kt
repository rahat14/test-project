package dev.syntext_error.testproject.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.syntext_error.testproject.R
import dev.syntext_error.testproject.models.QustionModel
import dev.syntext_error.testproject.utils.Utilities
import kotlinx.android.synthetic.main.fragment_mcq.*
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mcqFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class mcqFragment : Fragment() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mcq, container, false)
    }

    private var boolResp: String? = null
    private var spinnerResp: String? = null
    private var qusList: List<QustionModel>? = null
    private var isBolReq: Boolean = true
    private var isDropDownReq: Boolean = true
    private var dropDownResponse : String = "null"




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get the question we saved earlier
        // define views
        val Spinners = view.findViewById<Spinner>(R.id.spinners)
        qusList = Utilities.fetchArrayList(requireContext())

        /*
           * as the question response has a static amount question with different type of question
           * we will loop out specific question we need
           * as there is only one type of question each we can easily filler them out
           * we could also build the same thing with different item views in recycler view
           * But as all the qustion r limited that`s why i choose more of static approach
         */

        for (item in qusList!!) {
            if (item.type == "Checkbox") {
                if (item.required) BolQus.text = item.question + " *"
                else {
                    BolQus.text = item.question
                    isBolReq = false
                }
            } else if (item.type == "dropdown") {
                if (item.required) {
                    DropDownQus.text = item.question + " *"

                    // get the drop down
                    val optionString = item.options
                    // spilt it into words and get it into words
                    val optionsArray = optionString.split(",").toTypedArray()

                    val adapter = ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        optionsArray
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    Spinners.adapter = adapter

                } else {
                    DropDownQus.text = item.question
                    isDropDownReq = false
                }
            }


        }

        nextBtn.setOnClickListener {
            //change Fragment

            //findNavController().navigate(action_mcqFragment_to_otherQusFragment)

            if( isBolReq && boolResp.isNullOrEmpty() )
            {
                Toast.makeText(requireContext() , "Please Answer Correctly",Toast.LENGTH_LONG).show()
            }
            else if(isDropDownReq &&  dropDownResponse == "null")
            {
                Toast.makeText(requireContext() , "Please Answer Correctly",Toast.LENGTH_LONG).show()
            }
            else
            { //val action  = mcqFragmentDirections.actionMcqFragmentToOtherQusFragment()
                val bundle  = bundleOf("bolResponse" to BolQus.text.toString() + " : "  + boolResp , "dropDownResponse" to DropDownQus.text.toString() + " : "  + dropDownResponse)
               findNavController().navigate(R.id.action_mcqFragment_to_otherQusFragment, bundle)
            }
        }


        Spinners?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //println("error")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                dropDownResponse = parent?.getItemAtPosition(position).toString()


            }
        }

        yes.setOnClickListener{
            yes.isChecked = true
            no.isChecked = false
            boolResp = "true"
        }
        no.setOnClickListener{
            yes.isChecked = false
            no.isChecked = true
            boolResp = "no"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mcqFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mcqFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}