package com.mintic.ciclo4.appgroup13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoFragment : Fragment() {
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
        val fragmento : View = inflater.inflate(R.layout.fragment_to_do, container, false)

        val detail1 : Button = fragmento.findViewById(R.id.btn_detail_1)
        val detail2 : Button = fragmento.findViewById(R.id.btn_detail_2)
        val detail3 : Button = fragmento.findViewById(R.id.btn_detail_3)

        detail1.setOnClickListener{ it ->
            val datos = Bundle()
            datos.putString("tarea", resources.getString(R.string.txt_tarea_1))
            datos.putString("hora", resources.getString(R.string.txt_hora_1))
            datos.putString("lugar", resources.getString(R.string.txt_lugar_1))
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.my_fragment_container_view, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        }

        detail2.setOnClickListener{ it ->
            val datos = Bundle()
            datos.putString("tarea", resources.getString(R.string.txt_tarea_2))
            datos.putString("hora", resources.getString(R.string.txt_hora_2))
            datos.putString("lugar", resources.getString(R.string.txt_lugar_2))
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.my_fragment_container_view, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        }

        detail3.setOnClickListener{ it ->
            val datos = Bundle()
            datos.putString("tarea", resources.getString(R.string.txt_tarea_3))
            datos.putString("hora", resources.getString(R.string.txt_hora_3))
            datos.putString("lugar", resources.getString(R.string.txt_lugar_3))
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.my_fragment_container_view, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        }

        return fragmento
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ToDoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToDoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}