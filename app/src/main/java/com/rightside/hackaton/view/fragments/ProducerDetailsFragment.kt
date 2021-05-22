package com.rightside.hackaton.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rightside.hackaton.R
import com.rightside.hackaton.databinding.FragmentProducerBinding
import com.rightside.hackaton.databinding.FragmentProducerDetailsBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class ProducerDetailsFragment : Fragment(R.layout.fragment_producer_details) {
    lateinit var binding : FragmentProducerDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProducerDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.carousel.registerLifecycle(lifecycle)
        val list = mutableListOf<CarouselItem>(CarouselItem("https://emc-src.eptv.com.br/dbimagens/mato_alto_790x444_03012020165226.jpg", "aassa"), CarouselItem("https://emc-src.eptv.com.br/dbimagens/mato_alto_790x444_03012020165241.jpg", "aassa"), CarouselItem("https://s2.glbimg.com/PkAJ2BkF_dFex7M9JqMhJB8zUz4=/0x0:1920x1080/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2020/B/8/GLxRVASxmeYcMAtKACTw/nc-fazenda-itu-061220.jpg", "aassa"))
        binding.carousel.setData(list)
    }
}