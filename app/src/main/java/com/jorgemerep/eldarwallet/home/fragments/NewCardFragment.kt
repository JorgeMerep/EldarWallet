package com.jorgemerep.eldarwallet.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jorgemerep.eldarwallet.R
import com.jorgemerep.eldarwallet.databinding.FragmentNewCardBinding
import com.jorgemerep.eldarwallet.home.cards.Tarjeta
import com.jorgemerep.eldarwallet.viewmodel.HomeViewModel


class NewCardFragment : Fragment(R.layout.fragment_new_card) {

    private lateinit var binding: FragmentNewCardBinding
    private lateinit var viewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewCardBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        setupAddCardButton()
    }

    private fun setupAddCardButton() {
        binding.btnAgregarTarjeta.setOnClickListener {
            val numero = binding.numeroEditText.text.toString()
            val codigoSeguridad = binding.codigoEditText.text.toString()
            val vencimiento = binding.vencimientoEditText.text.toString()

            if (numero.isNotBlank() && codigoSeguridad.isNotBlank() && vencimiento.isNotBlank()) {
                val nombreTarjeta = when (numero.firstOrNull()) {
                    '3' -> "American Express"
                    '4' -> "Visa"
                    '5' -> "Mastercard"
                    else -> "Desconocido"
                }

                val nuevaTarjeta = Tarjeta(nombreTarjeta, numero, codigoSeguridad, vencimiento)
                viewModel.agregarNuevaTarjeta(nuevaTarjeta)
                findNavController().navigateUp()
            } else {
                // Mostrar un mensaje de error o validación
            }
        }
    }
}

    


