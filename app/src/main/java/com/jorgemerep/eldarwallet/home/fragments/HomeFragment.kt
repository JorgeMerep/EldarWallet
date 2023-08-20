package com.jorgemerep.eldarwallet.home.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jorgemerep.eldarwallet.R
import com.jorgemerep.eldarwallet.databinding.FragmentHomeBinding
import com.jorgemerep.eldarwallet.home.cards.TarjetasAdapter
import com.jorgemerep.eldarwallet.network.QRCodeGenerator
import com.jorgemerep.eldarwallet.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var tarjetasAdapter: TarjetasAdapter
    private val okHttpClient = OkHttpClient()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        setupRecyclerView()

        viewModel.tarjetasList.observe(viewLifecycleOwner) { tarjetas ->
            Log.d("HomeFragment", "Tarjetas observadas: ${tarjetas.size}")
            tarjetasAdapter.actualizarTarjetas(tarjetas)
        }


        binding.btnAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newCardFragment)
        }

        setupQRButton()

    }

    private fun setupRecyclerView() {
        tarjetasAdapter = TarjetasAdapter(emptyList())
        binding.recyclerViewTarjetas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = tarjetasAdapter
        }
    }

    private fun setupQRButton() {
        binding.btnQr.setOnClickListener {
            val qrCodeGenerator = QRCodeGenerator(okHttpClient)
            MainScope().launch {
                val data =
                    "https://www.neutrinoapi.com/signup/" // Cambia esta URL según tus necesidades
                val userId = "Jorge Merep" // Reemplaza con tu User ID
                val apiKey =
                    "ry3SixjiszAiFx5FdNPZ0rHzNoTVqWxV5EDG1heQSD3ZpXes" // Reemplaza con tu API Key

                Log.d("QRButton", "User ID: $userId")
                Log.d("QRButton", "API Key: $apiKey")

                val qrCodeBitmap: Bitmap? = withContext(Dispatchers.IO) {
                    qrCodeGenerator.generateQRCode(data, userId, apiKey)
                }

                if (qrCodeBitmap != null) {
                    val bundle = bundleOf("qr_bitmap" to qrCodeBitmap)
                    findNavController().navigate(R.id.action_homeFragment_to_qrFragment, bundle)
                } else {
                    Log.e("QRButton", "Error generating QR code")
                    // Mostrar un Toast en caso de error
                    Toast.makeText(
                        requireContext(),
                        "Error generando el código QR",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}


