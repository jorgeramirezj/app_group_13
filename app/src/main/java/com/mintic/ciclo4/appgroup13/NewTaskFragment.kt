package com.mintic.ciclo4.appgroup13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewTaskFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_new_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spiTask : Spinner = view.findViewById(R.id.spiTask)
        val edtTime : EditText = view.findViewById(R.id.edtTime)
        val edtPlace : EditText = view.findViewById(R.id.edtPlace)
        val btnNewTask : Button = view.findViewById(R.id.btnNewTask)
        // Agregar elementos a un spinner
        val arregloTareas = arrayOf(
            resources.getString(R.string.txt_select),
            resources.getString(R.string.txt_study),
            resources.getString(R.string.txt_shopping),
            resources.getString(R.string.txt_to_do_exercise))

        // Otra forma de cargar datos en el spinner, recordar que se modifico el toString de la clase "Task"
        var tareas : ArrayList<Task> = ArrayList()
        tareas.add(Task(resources.getString(R.string.txt_tarea_1), resources.getString(R.string.txt_hora_1), resources.getString(R.string.txt_lugar_1)))
        tareas.add(Task(resources.getString(R.string.txt_tarea_2), resources.getString(R.string.txt_hora_2), resources.getString(R.string.txt_lugar_2)))
        tareas.add(Task(resources.getString(R.string.txt_tarea_3), resources.getString(R.string.txt_hora_3), resources.getString(R.string.txt_lugar_3)))

        val taskAdapter = ArrayAdapter(
            context?.applicationContext!!,
            android.R.layout.simple_spinner_item,
            tareas)
        spiTask.adapter = taskAdapter
        btnNewTask.setOnClickListener{
            var tareaSeleccionada : Task = spiTask.selectedItem as Task
            // Esta forma muestra lo que un spinner tiene cargado
            //Toast.makeText(context?.applicationContext, spiTask.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            // Esta otra forma, con variable casteada, podemos seleccionar cualquier dato que esté enviando, recordar el override toString de la clase "Task"
            Toast.makeText(context?.applicationContext, tareaSeleccionada.place, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}