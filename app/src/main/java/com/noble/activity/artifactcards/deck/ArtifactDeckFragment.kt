package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.App
import com.noble.activity.artifactcards.app.decksPrefs
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckBinding
import com.noble.activity.artifactcards.deck.coder.ArtifactDeckDecoder
import com.noble.activity.artifactcards.deck.model.CardDeck
import com.noble.activity.artifactcards.deck.model.Deck
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.HERO_CARD_TYPE
import com.noble.activity.artifactcards.utils.InjectorUtils
import com.noble.activity.artifactcards.utils.runOnIoThread
import com.noble.activity.artifactcards.utils.showToast
import kotlinx.android.synthetic.main.artifact_frag_deck.*
import java.lang.Exception

class ArtifactDeckFragment : Fragment(), DeckDialog.Listener  {
    override fun onDeckConfirm(deckCode: String) {
        artifactDeckViewModel.loadDeckByCode(deckCode, viewLifecycleOwner, true)
    }

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

        binding.addDeckView.setOnClickListener {
            val deckDialog = DeckDialog()
            deckDialog.setListener(this)
            deckDialog.show(activity!!.supportFragmentManager, "deck_dialog")
        }

        val adapter = ArtifactDeckAdapter()
        binding.recyclerView.adapter = adapter
        subscribeUi(adapter)

        runOnIoThread {
            decksPrefs.refreshCards.forEach {
                   artifactDeckViewModel.loadDeckByCode(it, viewLifecycleOwner, false)
            }
        }

        return binding.root
    }

    private fun subscribeUi(adapter: ArtifactDeckAdapter) {
        artifactDeckViewModel.artifactDeckList.observe(viewLifecycleOwner, Observer { decks ->
            if (decks != null) {
                //adapter.submitList(null)
                adapter.submitList(decks)
                adapter.notifyDataSetChanged() // because fuck you submit list
            }
        })

    }




}