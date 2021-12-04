package com.mintic.ciclo4.appgroup13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.Room
import com.mintic.ciclo4.appgroup13.room_database.ToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerToDoFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_recycler_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerToDoList : RecyclerView = view.findViewById(R.id.recyclerToDoList)
        val datos : ArrayList<Task> = ArrayList()
        // ROOM
        val room : ToDoDatabase = Room.databaseBuilder(context?.applicationContext!!, ToDoDatabase::class.java, "ToDoDatabase")
            .build()
        var todoDao = room.todoDao()
        runBlocking {
            launch {
                var result = todoDao.getAllTasks()
                for (todo in result){
                    datos.add(Task(todo.id, todo.title, todo.time, todo.place))
                }
            }
        }
        /*datos.add(Task(1, resources.getString(R.string.txt_tarea_1),resources.getString(R.string.txt_hora_1),resources.getString(R.string.txt_lugar_1)))
        datos.add(Task(2, resources.getString(R.string.txt_tarea_2),resources.getString(R.string.txt_hora_2),resources.getString(R.string.txt_lugar_2)))
        datos.add(Task(3, resources.getString(R.string.txt_tarea_3),resources.getString(R.string.txt_hora_3),resources.getString(R.string.txt_lugar_3)))*/
        var taskAdapter = TaskAdapter(datos){
            val datos = Bundle()
            datos.putInt("id", it.id)
            /*datos.putString("tarea", it.task)
            datos.putString("hora", it.time)
            datos.putString("lugar", it.place)*/
            Navigation.findNavController(view).navigate(R.id.nav_detail, datos)
        }
        recyclerToDoList.setHasFixedSize(true)
        recyclerToDoList.adapter = taskAdapter
        recyclerToDoList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecyclerToDoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecyclerToDoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}