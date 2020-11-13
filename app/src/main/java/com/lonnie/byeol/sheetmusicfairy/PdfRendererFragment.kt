package com.lonnie.byeol.sheetmusicfairy

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

/**
 * Created By Lonnie on 2020/11/13
 *
 */

class PdfRendererFragment : Fragment(R.layout.fragment_pdf_render) {

    private val viewModel: PdfRendererViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // View references.
        val image: ImageView = view.findViewById(R.id.image)
        val buttonPrevious: Button = view.findViewById(R.id.previous)
        val buttonNext: Button = view.findViewById(R.id.next)

        // Bind data.
        viewModel.pageInfo.observe(viewLifecycleOwner, Observer { (index, count) ->
            activity?.title = getString(R.string.app_name_with_index, index + 1, count)
        })
        viewModel.pageBitmap.observe(viewLifecycleOwner, Observer { image.setImageBitmap(it) })
        viewModel.previousEnabled.observe(viewLifecycleOwner, Observer {
            buttonPrevious.isEnabled = it
        })
        viewModel.nextEnabled.observe(viewLifecycleOwner, Observer {
            buttonNext.isEnabled = it
        })

        // Bind events.
        buttonPrevious.setOnClickListener { viewModel.showPrevious() }
        buttonNext.setOnClickListener { viewModel.showNext() }
    }

}