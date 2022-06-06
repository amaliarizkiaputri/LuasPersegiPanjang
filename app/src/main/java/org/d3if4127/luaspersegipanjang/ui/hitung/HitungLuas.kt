package org.d3if4127.luaspersegipanjang.ui.hitung

import  android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import org.d3if4127.luaspersegipanjang.R
import org.d3if4127.luaspersegipanjang.databinding.FragmentLuasBinding
import org.d3if4127.luaspersegipanjang.db.PersegiPanjangDb
import org.d3if4127.luaspersegipanjang.model.HasilPersegiPanjang
import org.d3if4127.luaspersegipanjang.model.KategoriPersegiPanjang

class HitungLuas: Fragment() {

    private lateinit var binding: FragmentLuasBinding

    private val viewModel: HitungViewModel by lazy {
        val db = PersegiPanjangDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLuasBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return  binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.layout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.layout_menu -> {
                findNavController().navigate(R.id.action_hitungLuas_to_aboutFragment)
                true
            }
            R.id.histori_menu -> {
                findNavController().navigate(R.id.action_HitungLuas_to_historiFragment2)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungLuas() }
        binding.resetButton.setOnClickListener { resetHitungan() }
        binding.linkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://katadata.co.id/intan/berita/61780774934fa/rumus-luas-persegi-panjang-beserta-contoh-soalnya"))
            requireContext().startActivities(arrayOf(intent))
        }
        binding.lihatGambar.setOnClickListener { viewModel.mulaiNavigasi() }

        setupObserveres()

        viewModel.getHasilLuas().observe(requireActivity(), { showResult(it) })

        viewModel.getNavigasi().observe(viewLifecycleOwner) {
            if (it == null) return@observe
            findNavController().navigate(HitungLuasDirections.actionHitungLuasToAboutFragment())
            viewModel.selesaiNavigasi()
        }
    }


    @SuppressLint("StringFormatMatches")
    private fun showResult(result: HasilPersegiPanjang?) {
        if (result == null) return

        binding.luasTextView.text = getString(R.string.hasil_luas, result.luas)
        binding.kategoriTextView.text = getString(R.string.kategori, getKategoriLabel(result.kategoriPersegiPanjang))
        binding.lihatGambar.visibility = View.VISIBLE
        binding.linkButton.visibility = View.VISIBLE
    }

    private fun resetHitungan() {
        binding.panjangInp.text?.clear()
        binding.lebarInp.text?.clear()
        binding.luasTextView.text = ""
        binding.kategoriTextView.text = ""
        binding.lihatGambar.visibility = View.INVISIBLE
        binding.linkButton.visibility = View.INVISIBLE
    }

    private fun hitungLuas() {
        val panjang = binding.panjangInp.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val lebar = binding.lebarInp.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungLuas(
            panjang.toFloat(),
            lebar.toFloat()
        )
    }

    private fun getKategoriLabel(kategori: KategoriPersegiPanjang): String {
        val stringRes = when (kategori) {
            KategoriPersegiPanjang.KECIL -> R.string.kecil
            KategoriPersegiPanjang.SEDANG -> R.string.sedang
            KategoriPersegiPanjang.BESAR -> R.string.besar
        }
        return getString(stringRes)
    }

    private fun setupObserveres() {
        viewModel.getNavigasi().observe(viewLifecycleOwner, {
            if(it == null) return@observe
            findNavController().navigate(HitungLuasDirections.actionHitungFragmentToPersegiPanjangFragment(it))
            viewModel.selesaiNavigasi()
        })
        viewModel.getHasilLuas().observe(requireActivity(), { showResult(it) })
    }
}

private fun NavController.navigate(actionHitungFragmentToPersegiPanjangFragment: Unit) {

}
