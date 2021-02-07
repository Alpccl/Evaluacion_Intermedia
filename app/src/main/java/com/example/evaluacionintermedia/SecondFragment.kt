package com.example.evaluacionintermedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.evaluacionintermedia.ViewModel.TaskViewModel
import com.example.evaluacionintermedia.databinding.FragmentSecondBinding
import com.example.evaluacionintermedia.model.TaskEntity

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel : TaskViewModel by activityViewModels()
    private var idTask : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idTask = it.getInt("id", -1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            saveTask()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(com.example.evaluacionintermedia.R.id.action_SecondFragment_to_FirstFragment)
        }
        viewModel.getTaskById(idTask).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.edProducto.setText(it.nombre)
                binding.edPrecio.setText(it.precio)
                binding.NumerPicker.toString()
            }

        })

    }


   private fun saveTask(){
       val nombre = binding.edProducto.text.toString()
       val precio = binding.edPrecio.toString()
       val cantidad = binding.NumerPicker.toString()
       if (nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty()){
           Toast.makeText(context, "Porfavor ingrese los datos necesitados",
               Toast.LENGTH_LONG).show()
       }else{
           if (idTask == -1){
               val newTask = TaskEntity(nombre = nombre, precio = precio, cantidad = cantidad )
               viewModel.insertTask((newTask))
           }else{//jelementos int no permite almacenaje
               val updateTask = TaskEntity(
                   id = idTask, nombre = nombre, precio = precio,
                   cantidad = cantidad )
               viewModel.updateTask(updateTask)
           }
       }
   }
}


