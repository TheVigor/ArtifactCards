package com.noble.activity.artifactcards.deck

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.deck.coder.ArtifactDeckDecoder
import com.noble.activity.artifactcards.utils.showToast
import kotlinx.android.synthetic.main.deck_dialog.view.*


class DeckDialog: DialogFragment() {
    private lateinit var mListener: Listener
    interface Listener {
        fun onDeckConfirm(deckCode: String)
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = activity!!.layoutInflater.inflate(R.layout.deck_dialog, null)
        return AlertDialog.Builder(context!!)
            .setView(view)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val deckCode = view.deck_input.text.trim().toString()
                if (deckCode.startsWith(ArtifactDeckDecoder.encodePrefix)) {
                    mListener.onDeckConfirm(deckCode)
                } else {
                    activity?.showToast(getString(R.string.invalid_deck_code))
                }
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->

            }
            .setTitle(R.string.enter_deck_code)
            .create()
    }
}