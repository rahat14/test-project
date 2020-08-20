package dev.syntext_error.testproject.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dev.syntext_error.testproject.views.MainActivity
import dev.syntext_error.testproject.R
import dev.syntext_error.testproject.models.QustionModel
import dev.syntext_error.testproject.models.AnswersModel
import dev.syntext_error.testproject.utils.Utilities
import dev.syntext_error.testproject.viewmodel.ResponsesViewModel
import kotlinx.android.synthetic.main.fragment_other_qus.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [otherQusFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class otherQusFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var qusList: List<QustionModel>? = null
    private var textRequires: Boolean = false
    private var numberRequires: Boolean = false
    private var mcqRequires: Boolean = false
    private var responseViewModel: ResponsesViewModel? = null
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
        return inflater.inflate(R.layout.fragment_other_qus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        responseViewModel = ViewModelProvider(this).get(ResponsesViewModel::class.java)
        // getting the qustion
        qusList = Utilities.fetchArrayList(requireContext())


        // now looping it for the selected question

        for (item in qusList!!) {

            if (item.type == "text") {
                if (item.required) {
                    textRequires = true

                    textQus.text = item.question + " *"

                } else textQus.text = item.question

            } else if (item.type == "number") {
                if (item.required) {
                    numberRequires = true

                    numberQus.text = item.question + " *"
                } else numberQus.text = item.question


            } else if (item.type == "multiple choice") {

                var qus = item.question;
                if (item.required) {
                    mcqRequires = true
                    qus = qus + " *"
                }

                val optionsArray = item.options.split(",").toTypedArray()

                radioButton.text = optionsArray[0]
                radioButton2.text = optionsArray[1]
                radioButton3.text = optionsArray[2]
                radioButton4.text = optionsArray[3]

                mcqQus.text = qus

            }


        }


        submit.setOnClickListener {
            var textResp = textResponse.text.toString()
            var numberResp = numberResponse.text.toString()
            var mcqResponse: String = "null"
            // getting the radio response
            var id: Int = radio_group.checkedRadioButtonId
            if (id != -1) { // If any radio button checked from radio group
                // Get the instance of radio button using id

                val radio: RadioButton = view.findViewById(id)
                mcqResponse = radio.text.toString()
            } else {
                // If no radio button checked in this radio group
                mcqResponse = "null"
            }


            if (textRequires && textResp.isEmpty()) {
                Toast.makeText(requireContext(), "Required", Toast.LENGTH_LONG).show()
            } else if (numberRequires && numberResp.isEmpty()) {
                Toast.makeText(requireContext(), "Required", Toast.LENGTH_LONG).show()
            } else if (mcqRequires && mcqResponse == "null") {
                Toast.makeText(requireContext(), "Required", Toast.LENGTH_LONG).show()
            }
            // save the response on the room database
            // 1st create the model
            // get the data from the argumen
            else {
                val bolResp = arguments?.getString("bolResponse").toString()

                val response = AnswersModel(0 ,bolResp , textQus.text.toString()  + " : "  + textResp.toString() ,mcqQus.text.toString() + " : "  +  mcqResponse  ,
                    numberQus.text.toString()+ " : "  + numberResp.toString()
                    , arguments?.getString("dropDownResponse").toString() ,Utilities.getDate().toString())  ;

                responseViewModel?.setMessage(response)


                findNavController().navigate(R.id.action_otherQusFragment_to_done_fragment, null)

            }

        }

        back.setOnClickListener {
            findNavController().popBackStack(R.id.mcqFragment, false)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment otherQusFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            otherQusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}