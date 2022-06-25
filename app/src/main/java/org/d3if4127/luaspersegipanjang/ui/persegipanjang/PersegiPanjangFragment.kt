package org.d3if4127.luaspersegipanjang.ui.persegipanjang

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import org.d3if4127.luaspersegipanjang.R
import org.d3if4127.luaspersegipanjang.databinding.FragmentLihatGambarBinding
import org.d3if4127.luaspersegipanjang.model.KategoriPersegiPanjang
import org.d3if4127.luaspersegipanjang.util.Gambar

class PersegiPanjangFragment: Fragment() {

    private lateinit var binding: FragmentLihatGambarBinding
    private val args: PersegiPanjangFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLihatGambarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun updateUI(kategori: KategoriPersegiPanjang) {
        when (kategori) {
            KategoriPersegiPanjang.KECIL -> {
                Glide.with(requireActivity())
                    .load(Uri.parse(Gambar.persegipanjangkecil))
                    .into(binding.imageView)
                binding.textView.text = getString(R.string.desc_ppkecil)
            }
            KategoriPersegiPanjang.SEDANG -> {
                Glide.with(requireActivity())
                    .load(Uri.parse(Gambar.persegipanjangsedang))
                    .into(binding.imageView)
                binding.textView.text = getString(R.string.desc_ppsedang)
            }
            KategoriPersegiPanjang.BESAR -> {
                Glide.with(requireActivity())
                    .load(Uri.parse(Gambar.persegipanjangbesar))
                    .into(binding.imageView)
                binding.textView.text = getString(R.string.desc_ppbesar)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.kategori)
    }
}


