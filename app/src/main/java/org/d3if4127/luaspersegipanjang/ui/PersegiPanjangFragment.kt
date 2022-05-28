package org.d3if4127.luaspersegipanjang.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if4127.luaspersegipanjang.R
import org.d3if4127.luaspersegipanjang.databinding.FragmentLihatGambarBinding
import org.d3if4127.luaspersegipanjang.model.KategoriPersegiPanjang

class PersegiPanjangFragment: Fragment() {

    private lateinit var binding: FragmentLihatGambarBinding
    private val args: PersegiPanjangFragmentArgs by navArgs()

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLihatGambarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI (kategori: KategoriPersegiPanjang) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriPersegiPanjang.KECIL -> {
                binding.imageView.setImageResource(R.drawable.ppkecil)
                binding.textView.text = getString(R.string.desc_ppkecil)
            }
            KategoriPersegiPanjang.SEDANG -> {
                binding.imageView.setImageResource(R.drawable.ppsedang)
                binding.textView.text = getString(R.string.desc_ppsedang)
            }
            KategoriPersegiPanjang.BESAR -> {
                binding.imageView.setImageResource(R.drawable.ppbesar)
                binding.textView.text = getString(R.string.desc_ppbesar)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }
}

interface PersegiPanjangFragmentArgs {

    abstract val kategori: KategoriPersegiPanjang
}
