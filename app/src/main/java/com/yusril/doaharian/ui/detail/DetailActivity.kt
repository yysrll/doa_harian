package com.yusril.doaharian.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.yusril.doaharian.R
import com.yusril.doaharian.core.domain.model.Doa
import com.yusril.doaharian.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    companion object {
        const val DETAIL_DOA = "detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail = intent.getParcelableExtra<Doa>(DETAIL_DOA)
        showDetail(detail)
    }

    private fun showDetail(detail: Doa?) {
        detail?.let {
            binding.doaTitle.text = detail.title
            binding.doaLatin.text = detail.latin
            binding.doaTranslate.text = detail.translation

            var isFavorite = detail.isFavorite
            setStatusFavorite(isFavorite)
            binding.fabAdd.setOnClickListener {
                isFavorite = !isFavorite
                viewModel.setFavoriteDoa(detail, isFavorite)
                setStatusFavorite(isFavorite)
            }
        }
    }

    private fun setStatusFavorite(favorite: Boolean) {
        if (favorite) {
            binding.fabAdd.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            binding.fabAdd.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }
}