package com.example.geomob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.geomob.R
import com.example.geomob.adapter.YoutubeVideoAdapter.YoutubeVideoHolder
import com.google.android.material.card.MaterialCardView

class YoutubeVideoAdapter(): RecyclerView.Adapter<YoutubeVideoHolder>() {

    companion object{
        private var youtubeVideoList:List<String> =ArrayList()
        //lateinit var listner: YoutubeVideoAdapter.OnItemClickListner
    }

    class YoutubeVideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val  web_view: WebView =itemView.findViewById(R.id.web_view_youtube)
        internal val  source_item: ImageView =itemView.findViewById(R.id.source_item)
        internal val card: MaterialCardView =itemView.findViewById(R.id.card_youtube)
        init {

            /*itemView.setOnClickListener(View.OnClickListener {
                val position=adapterPosition
                if(position!= RecyclerView.NO_POSITION){

                    listner.onItemClick(youtubeVideoList.get(position))
                }
            })*/

            web_view.setWebChromeClient(WebChromeClient())
            web_view.setWebViewClient(WebViewClient())
            web_view.getSettings().setAppCacheEnabled(true)
            web_view.getSettings().setJavaScriptEnabled(true)

        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YoutubeVideoHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.youtube_video_item,parent,false)

        return YoutubeVideoHolder(itemView)
    }

    override fun getItemCount(): Int {
       return youtubeVideoList.size
    }


    fun setYoutubeVideo(spiderItemListt:List<String>)
    {
        youtubeVideoList =spiderItemListt
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: YoutubeVideoHolder, position: Int) {
        val currentSpiderItemList: String = youtubeVideoList.get(position)
        val map :Map<String,String> = mapOf(Pair("text/html","utf-8"))

            holder.source_item.setImageResource(R.drawable.ic_youtube)
            holder.web_view.loadUrl(currentSpiderItemList,map)

        if(youtubeVideoList.size<2){
            val layoutParams: FrameLayout.LayoutParams
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
                android.R.attr.top
            )
            holder.card.layoutParams=layoutParams
        }
    }

    /*interface OnItemClickListner{
        fun onItemClick(spiderItem: SpiderItem)
    }

    fun SetOnItemClickListner(listnerr:OnItemClickListner){
        VideoSpiderAdapter.listner =listnerr
    }*/

}