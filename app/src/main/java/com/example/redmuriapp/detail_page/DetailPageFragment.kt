package com.example.redmuriapp.detail_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.redmuriapp.databinding.FragmentDetailPageBinding
import com.example.redmuriapp.main_page.LatestItemsAdapter


class DetailPageFragment : Fragment() {

    private val args by navArgs<DetailPageFragmentArgs>()

    private var _binding: FragmentDetailPageBinding? = null
    private val binding: FragmentDetailPageBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailPageBinding == null")

    private val adapterItemImages by lazy {
        ItemImagesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadItemData()
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.vpPhotos.adapter = adapterItemImages
        adapterItemImages.images = listOf(
            "https://avatars.mds.yandex.net/i?id=653e4ace5119cbfef916f2890c48f1d6794c91ed-6926872-images-thumbs&n=13&exp=1",
            "https://avatars.mds.yandex.net/i?id=835f93ab5c528912509e77a34898ff43-5018134-images-thumbs&n=13&exp=1",
            "https://sun9-85.userapi.com/impg/GqVqFLRKJbOWyK9NRt2bDmssR2iVZQQsBunnfA/2ZslYJKru30.jpg?size=1080x720&quality=95&sign=a71e1453b164a8059c20114e3af234b8&c_uniq_tag=Wva_p5JuKSe6GiUS-nHmTsqrnFQTfhByexD6p36nqVE&type=album"
        )
        adapterItemImages.notifyDataSetChanged()
    }

    private fun uploadItemData() {
        val item = args.LatestItem
        binding.tvItemName.text = item.name
        binding.tvItemPrice.text = item.price.toString()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
