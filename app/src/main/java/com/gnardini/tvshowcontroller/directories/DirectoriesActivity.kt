package com.gnardini.tvshowcontroller.directories

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import com.gnardini.tvshowcontroller.R
import com.gnardini.tvshowcontroller.TvShowControllerApp
import com.gnardini.tvshowcontroller.animation.ControlsAnimationManager
import com.gnardini.tvshowcontroller.extensions.addTextWatcher
import com.gnardini.tvshowcontroller.extensions.setClickListener
import com.gnardini.tvshowcontroller.extensions.showToast
import com.gnardini.tvshowcontroller.kotterknife.bindView
import com.gnardini.tvshowcontroller.utils.KeyboardUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DirectoriesActivity : AppCompatActivity() {

    private val commonInjector = TvShowControllerApp.commonInjector

    private val controllerService = commonInjector.getControllerService()
    private val hostManager = commonInjector.hostManager
    private val controllerInteractor = commonInjector.controllerInteractors
    private var currentPath = "~"
    private lateinit var controlsAnimator: ControlsAnimationManager
    private lateinit var directoryAdapter: DirectoryAdapter

    private val directories: RecyclerView by bindView(R.id.directories)
    private val serverIp: EditText by bindView(R.id.server_ip)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.directory_activity)
        setupRecyclerView()
        setListeners()
        setupServerIpListeners()
        setupControls()
        fetchDirectories()
    }

    private fun setupRecyclerView() {
        directoryAdapter = DirectoryAdapter({ directory ->
            currentPath += "/" + directory
            fetchDirectories()
        })
        directories.adapter = directoryAdapter
        directories.layoutManager = LinearLayoutManager(this)
    }

    private fun setListeners() {
        setClickListener(R.id.show_controls, this::toggleControls)
        setClickListener(R.id.maximize, controllerInteractor::maximize)
        setClickListener(R.id.play_pause, controllerInteractor::playOrPause)
        setClickListener(R.id.volume_up, controllerInteractor::volumeUp)
        setClickListener(R.id.volume_down, controllerInteractor::volumeDown)
        setClickListener(R.id.rewind, controllerInteractor::rewind)
        setClickListener(R.id.fast_forward, controllerInteractor::fastForward)
    }

    private fun setupControls() {
        val controls = findViewById<View>(R.id.controls)
        controls.post {
            controlsAnimator = ControlsAnimationManager(
                    controls, findViewById(R.id.show_controls))
        }
    }

    private fun fetchDirectories() {
        controllerService
                .fetch(currentPath)
                .enqueue(object : Callback<List<String>> {
                    override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                        if (response.isSuccessful) {
                            val directories = response.body() ?: emptyList()
                            if (directories.contains(currentDirectory)) {
                                undoLast()
                            }
                            directoryAdapter.setDirectories(directories)
                        } else {
                            showToast("Error with the server")
                        }
                    }

                    override fun onFailure(call: Call<List<String>>, t: Throwable) {
                        showToast("Error with the server")
                    }
                })
    }

    private fun toggleControls() {
        KeyboardUtils.hideKeyboard(serverIp)
        controlsAnimator.toggle()
    }

    private fun setupServerIpListeners() {
        serverIp.setText(hostManager.getServerIp())
        serverIp.setOnClickListener { _ -> refreshServerIp() }
        serverIp.addTextWatcher(hostManager::saveServerIp)
    }

    private fun refreshServerIp() {
        commonInjector.retrofitServices.refresh()
    }

    override fun onBackPressed() {
        val path = currentPath.split("/")
        if (path.size == 1) {
            super.onBackPressed()
        } else {
            undoLast()
            fetchDirectories()
        }
    }

    private fun undoLast() {
        val path = currentPath.split("/")
        val pathBuffer = StringBuilder()
        for (i in 0..path.size - 2) {
            pathBuffer.append(path[i])
            if (i != path.size - 2) {
                pathBuffer.append("/")
            }
        }
        currentPath = pathBuffer.toString()
    }

    private val currentDirectory: String
        get() {
            val path = currentPath.split("/")
            return path[path.size - 1]
        }

}
