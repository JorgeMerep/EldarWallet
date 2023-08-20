package com.jorgemerep.eldarwallet.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jorgemerep.eldarwallet.R
import com.jorgemerep.eldarwallet.databinding.FragmentCreateUsserBinding
import com.jorgemerep.eldarwallet.login.data.AppDatabase
import com.jorgemerep.eldarwallet.login.data.UserEntity
import com.jorgemerep.eldarwallet.login.data.UserRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class CreateUsserFragment : Fragment(R.layout.fragment_create_usser) {

    private lateinit var userRepository: UserRepository
    private lateinit var binding: FragmentCreateUsserBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateUsserBinding.bind(view)
        userRepository = UserRepository(AppDatabase.getInstance(requireContext()).userDao())

        binding.btnCreateUsser.setOnClickListener {
            val fullName = binding.fullNameEditText.text.toString()
            val dni = binding.dniEditText.text.toString()

            if (fullName.isNotEmpty() && dni.isNotEmpty()) {
                val user = UserEntity(fullName = fullName, dni = dni)
                MainScope().launch {
                    userRepository.insertUser(user)

                    // Indica al usuario que se ha creado exitosamente.
                    Toast.makeText(requireContext(), "Usuario creado exitosamente.", Toast.LENGTH_SHORT).show()
                    // Navega de regreso al fragmento de inicio de sesión
                    findNavController().navigate(R.id.action_createUsserFragment_to_loginFragment)
                }
            } else {
                // Muestra un mensaje de error indicando que se deben completar ambos campos.
                Toast.makeText(requireContext(), "Por favor, completa ambos campos.", Toast.LENGTH_SHORT).show()
            }
        }

        val btn_return = this.requireView().findViewById(R.id.btn_return) as Button
        btn_return.setOnClickListener {
            findNavController().navigate(R.id.action_createUsserFragment_to_loginFragment)
        }
    }
}



