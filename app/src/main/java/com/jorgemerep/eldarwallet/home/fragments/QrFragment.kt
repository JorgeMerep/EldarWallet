package com.jorgemerep.eldarwallet.home.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jorgemerep.eldarwallet.databinding.FragmentQrBinding


class QrFragment : Fragment() {

    private lateinit var binding: FragmentQrBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val qrBitmap: Bitmap? = arguments?.getParcelable("qr_bitmap")
        qrBitmap?.let {
            binding.qrImageView.setImageBitmap(it)
        }
    }
}