package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.app.App
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckBinding
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckDetailBinding

class ArtifactDeckDetailFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(): ArtifactDeckDetailFragment {
            return ArtifactDeckDetailFragment()
        }
    }

    //private lateinit var artifactCardDetailViewModel: ArtifactCardDetailViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = ArtifactFragDeckDetailBinding.inflate(inflater, container, false)
        binding.cardDeck = App.cardDeck

        val adapter = ArtifactDeckDetailAdapter()
        binding.recyclerView.adapter = adapter
        adapter.submitList(App.cardDeck.cards)


        return binding.root

//        val factory = InjectorUtils.provideArtifactCardDetailViewModelFactory(newId, newName, firstRefId, secondRefId, thirdRefId)
//        artifactCardDetailViewModel = ViewModelProviders.of(this, factory)
//            .get(ArtifactCardDetailViewModel::class.java)
//        initLiveData()

//        val binding = DataBindingUtil.inflate<ArtifactFragCardDetailBinding>(
//            inflater, R.layout.artifact_frag_card_detail, container, false).apply {
//            viewModel = artifactCardDetailViewModel
//            setLifecycleOwner(this@ArtifactCardDetailFragment)
//        }
//
//        artifactCardDetailViewModel.getCardPrice()

//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setToolbar(detail_toolbar)
    }

    private fun setToolbar(toolbar: Toolbar?) {
        if (toolbar == null) {
            return
        }
        val activity = activity as AppCompatActivity?
        activity!!.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener { activity.onBackPressed() })

        val actionBar = activity.supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

//    private fun initLiveData() {
//
//        artifactCardDetailViewModel.priceLiveData.observe(this@ArtifactCardDetailFragment,
//            Observer { price ->
//                price?.let {
//                    card_price.text = price
//                }
//            })
//    }
}