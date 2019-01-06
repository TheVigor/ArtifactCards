package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckBinding
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.InjectorUtils

class ArtifactDeckFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ArtifactDeckFragment()
    }

    private lateinit var artifactDeckViewModel: ArtifactDeckViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ArtifactFragDeckBinding.inflate(inflater, container, false)

        val factory = InjectorUtils.provideArtifactDeckViewModelFactory()

        artifactDeckViewModel = ViewModelProviders
            .of(this, factory)
            .get(ArtifactDeckViewModel::class.java)

        val adapter = ArtifactDeckAdapter()
        binding.recyclerView.adapter = adapter

        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: ArtifactDeckAdapter) {
        artifactDeckViewModel.getDecks().observe(viewLifecycleOwner, Observer { cards ->
            if (cards != null) {
                adapter.submitList(cards)
            }
        })
    }




}