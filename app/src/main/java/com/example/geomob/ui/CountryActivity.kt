package com.example.geomob.ui

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.geomob.network.NetworkConnection
import com.example.geomob.R
import com.example.geomob.adapter.CountryPagerAdapter
import com.example.geomob.adapter.TwitterTweetAdapter
import com.example.geomob.adapter.YoutubeVideoAdapter
import com.example.geomob.db.entity.Country
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_country.*


class CountryActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var runnable:Runnable
    private var pause:Boolean = false


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        intent = intent
        val country = intent.getSerializableExtra("country") as Country

        // set up the view pager
        val viewPager = country_view_pager
        val adapter = CountryPagerAdapter(
            supportFragmentManager,
            country.images
        )
        viewPager.adapter = adapter

        Picasso.get().load(country.flag).into(country_flag)
        country_name.text = country.name
        country_description.text = country.description
        country_surface.text = "${country.surface} million km²"
        country_population.text = "${country.population} million (2019)"

        mediaPlayer.setDataSource(country.anthemUrl)
        mediaPlayer.prepareAsync()

        pauseBtn.isEnabled = false
        pauseBtn.imageAlpha = 50

        stopBtn.isEnabled = false
        stopBtn.imageAlpha = 50

        // Start the media player
        playBtn.setOnClickListener{
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
            }else{
                mediaPlayer.start()
            }
            playBtn.isEnabled = false
            playBtn.imageAlpha = 50

            pauseBtn.isEnabled = true
            pauseBtn.imageAlpha = 1000

            stopBtn.isEnabled = true
            stopBtn.imageAlpha = 1000



            mediaPlayer.setOnCompletionListener {
                playBtn.isEnabled = true
                playBtn.imageAlpha = 1000

                pauseBtn.isEnabled = false
                pauseBtn.imageAlpha = 50

                stopBtn.isEnabled = false
                stopBtn.imageAlpha = 50

            }
        }


        // Pause the media player
        pauseBtn.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
                pause = true
                playBtn.isEnabled = true
                playBtn.imageAlpha = 1000


                pauseBtn.isEnabled = false
                pauseBtn.imageAlpha = 50

                stopBtn.isEnabled = true
                stopBtn.imageAlpha = 1000

            }
        }


        // Stop the media player
        stopBtn.setOnClickListener{
            if(mediaPlayer.isPlaying || pause.equals(true)){
                pause = false
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()

                playBtn.isEnabled = true
                playBtn.imageAlpha = 1000

                pauseBtn.isEnabled = false
                pauseBtn.imageAlpha = 50

                stopBtn.isEnabled = false
                stopBtn.imageAlpha = 50
            }
        }



        val recyclerViewYoutube: RecyclerView = youtube_list as RecyclerView
        recyclerViewYoutube.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewYoutube.layoutManager = linearLayoutManager


        val adapte = YoutubeVideoAdapter()


        val recyclerViewTwitter: RecyclerView = twitter_list as RecyclerView
        recyclerViewTwitter.setHasFixedSize(true)
        val linearLayoutManager1 = LinearLayoutManager(this)
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewTwitter.layoutManager = linearLayoutManager1


        val adapte1 = TwitterTweetAdapter()


        val networkConnection= NetworkConnection(this)
        networkConnection.observe(this, Observer {isConnected->
            if (isConnected){
                progressBar_loading.visibility= View.GONE
                adapte.setYoutubeVideo(country.youtubeVideo)
                recyclerViewYoutube.adapter = adapte

                adapte1.setTwitterTweet(country.twitterTweets)
                recyclerViewTwitter.adapter=adapte1

            }else{
                progressBar_loading.visibility= View.VISIBLE
            }
        })



    }
}
