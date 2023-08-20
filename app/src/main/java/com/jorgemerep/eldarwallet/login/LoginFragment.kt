package com.jorgemerep.eldarwallet.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jorgemerep.eldarwallet.R
import com.jorgemerep.eldarwallet.databinding.FragmentLoginBinding
import com.jorgemerep.eldarwallet.login.data.AppDatabase
import com.jorgemerep.eldarwallet.login.data.UserRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var userRepository: UserRepository
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)
        userRepository = UserRepository(AppDatabase.getInstance(requireContext()).userDao())

        binding.btnLogin.setOnClickListener {

            val dni = binding.passwordEditText.text.toString()
            val fullName = binding.userEditText.text.toString()

            if (fullName.isNotEmpty() && dni.isNotEmpty()) {
                MainScope().launch {
                    val user = userRepository.getUserByFullNameAndDni(fullName, dni)
                    if (user != null) {
                        //Si los datos coinciden, navegar al HomeFragment
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        // Mostrar un mensaje de error indicando que las credenciales son incorrectas.
                        Toast.makeText(
                            requireContext(),
                            "Credenciales incorrectas. Inténtalo nuevamente.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                // Mostrar un mensaje de error indicando que se deben ingresar el nombre, apellido y DNI.
                Toast.makeText(
                    requireContext(),
                    "Por favor, ingresa tu nombre, apellido y DNI.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            val btn_registrate = this.requireView().findViewById(R.id.btn_registrate) as TextView
            btn_registrate.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_createUsserFragment)

            }
        }
    }
}



