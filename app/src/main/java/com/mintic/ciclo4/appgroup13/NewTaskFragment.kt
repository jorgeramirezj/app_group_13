package com.mintic.ciclo4.appgroup13

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.room.*
import com.mintic.ciclo4.appgroup13.room_database.ToDoDatabase
import com.mintic.ciclo4.appgroup13.room_database.entity.ToDo
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
        tareas.add(Task(1, resources.getString(R.string.txt_to_do_exercise), "6:00", resources.getString(R.string.txt_gym)))
        tareas.add(Task(2, resources.getString(R.string.txt_study), "8:00", resources.getString(R.string.txt_university)))
        tareas.add(Task(3, resources.getString(R.string.txt_shopping), "14:00", resources.getString(R.string.txt_supermarket)))

        val taskAdapter = ArrayAdapter(
            context?.applicationContext!!,
            android.R.layout.simple_spinner_item,
            tareas) // aqui se modifica para ver la variable de "tareas" o "arregloTareas" => recordar que se modifico para ver en la clase "Task"
        spiTask.adapter = taskAdapter
        btnNewTask.setOnClickListener{
            // Muestra lo que un spinner tiene cargado
            /*Toast.makeText(context?.applicationContext!!, spiTask.selectedItem.toString(), Toast.LENGTH_SHORT).show()*/
            // Con variable casteada, podemos seleccionar cualquier dato que esté enviando, recordar el override toString de la clase "Task"
            var tareaSeleccionada : Task = spiTask.selectedItem as Task
            /*Toast.makeText(context?.applicationContext!!, tareaSeleccionada.task, Toast.LENGTH_SHORT).show()*/
            // ROOM
            val room: ToDoDatabase = Room.databaseBuilder(context?.applicationContext!!, ToDoDatabase::class.java, "ToDoDatabase")
                .build()
            val task = ToDo(0, tareaSeleccionada.task.toString(), edtTime.text.toString(), edtPlace.text.toString())
            val todoDao = room.todoDao()
            // COROUTINE
            runBlocking {
                launch {
                    var result = todoDao.insertTask(task)
                    Toast.makeText(context?.applicationContext!!, "" + result, Toast.LENGTH_LONG).show()
                }
            }
            Navigation.findNavController(view).navigate(R.id.nav_todo_recycler)
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