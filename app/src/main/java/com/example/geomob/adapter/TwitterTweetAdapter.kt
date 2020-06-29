package com.example.geomob.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.geomob.R
import com.google.android.material.card.MaterialCardView



class TwitterTweetAdapter(): RecyclerView.Adapter<TwitterTweetAdapter.TwitterTweetHolder>() {

    companion object{
        private var twitterTweetList:List<String> =ArrayList()
        //lateinit var listner: YoutubeVideoAdapter.OnItemClickListner
    }

    class TwitterTweetHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val  web_view: WebView =itemView.findViewById(R.id.web_view_twitter)
        internal val  source_item: ImageView =itemView.findViewById(R.id.source_item)
        internal val card: MaterialCardView =itemView.findViewById(R.id.card_twitter)
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
    ): TwitterTweetHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.twitter_tweet_item,parent,false)

        return TwitterTweetHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return twitterTweetList.size
    }


    fun setTwitterTweet(spiderItemListt:List<String>)
    {
        twitterTweetList =spiderItemListt
        notifyDataSetChanged()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: TwitterTweetHolder, position: Int) {
        val currentSpiderItemList: String = twitterTweetList.get(position)

        holder.web_view.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })
            holder.source_item.setImageResource(R.drawable.ic_twitter)
            holder.web_view.loadDataWithBaseURL("https://twitter.com","<blockquote class=\"twitter-tweet\">\n" +
                    "   <p lang=\"en\" dir=\"ltr\"> <a href=\"https://t.co/sAcZtUUtB8\"></a></p>\n" +
                    " <a href=\""+currentSpiderItemList+"\"></a>\n" +
                    "</blockquote>\n" +
                    "<script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>","text/html","utf-8","")

        /*if(twitterTweetList.size<2){
            val layoutParams: FrameLayout.LayoutParams
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT,
                android.R.attr.top
            )
            holder.card.layoutParams=layoutParams*/
        }



    }

    /*interface OnItemClickListner{
        fun onItemClick(spiderItem: SpiderItem)
    }

    fun SetOnItemClickListner(listnerr:OnItemClickListner){
        VideoSpiderAdapter.listner =listnerr
    }*/

