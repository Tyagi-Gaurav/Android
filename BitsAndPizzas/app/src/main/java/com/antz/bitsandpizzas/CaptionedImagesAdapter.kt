package com.antz.bitsandpizzas

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CaptionedImagesAdapter(val captions: Array<String>,
                             val imageIds: Array<Int>)
    : RecyclerView.Adapter<ViewHolder>() {

    private var listener: Listener? = null

    fun setListener(plistener: Listener?) {
        this.listener = plistener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_captioned_image, viewGroup, false) as CardView
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int = captions.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val cardView = viewHolder.view
        val imageView = cardView.findViewById<ImageView>(R.id.info_image)
        val drawable = ContextCompat.getDrawable(cardView.context, imageIds[position])
        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[position]
        val textView = cardView.findViewById<TextView>(R.id.info_text)
        textView.setText(captions[position])
        cardView.setOnClickListener(View.OnClickListener {
            listener!!.onClick(position)
        })
    }
}

/*
The view holder is used to define what view or views the recycler view should use for each data item it’s given.
 */
class ViewHolder(val view: CardView) : RecyclerView.ViewHolder(view)

interface Listener {
    fun onClick(position: Int)
}