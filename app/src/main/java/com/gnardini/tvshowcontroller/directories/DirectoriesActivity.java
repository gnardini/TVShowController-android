package com.gnardini.tvshowcontroller.directories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gnardini.tvshowcontroller.R;
import com.gnardini.tvshowcontroller.animation.ControlsAnimationManager;
import com.gnardini.tvshowcontroller.networking.ControllerService;
import com.gnardini.tvshowcontroller.networking.HostManager;
import com.gnardini.tvshowcontroller.utils.KeyboardUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DirectoriesActivity extends AppCompatActivity {

    private ControllerService controllerService;
    private HostManager hostManager;
    private String currentPath;
    private ControlsAnimationManager controlsAnimator;

    private DirectoryAdapter directoryAdapter;

    @BindView(R.id.directories) RecyclerView directories;
    @BindView(R.id.server_ip) TextView serverIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory_activity);
        ButterKnife.bind(this);
        setupHostManagement();
        setupControls();
        setupRecyclerView();
        currentPath = "~";
        fetchDirectories();
    }

    private void setupRecyclerView() {
        directoryAdapter = new DirectoryAdapter(new DirectoryClickListener() {
            @Override
            public void onDirectoryClicked(String directory) {
                currentPath += "/" + directory;
                fetchDirectories();
            }
        });
        directories.setAdapter(directoryAdapter);
        directories.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fetchDirectories() {
        controllerService
                .fetch(currentPath)
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        if (response.isSuccessful()) {
                            List<String> directories = response.body();
                            if (directories.contains(getCurrentDirectory())) {
                                undoLast();
                            }
                            directoryAdapter.setDirectories(directories);
                        } else {
                            Toast.makeText(DirectoriesActivity.this, "Error with the server", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Toast.makeText(DirectoriesActivity.this, "Error with the server", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick(R.id.show_controls)
    void toggleControls() {
        KeyboardUtils.hideKeyboard(serverIp);
        controlsAnimator.toggle();
    }

    @OnClick(R.id.maximize)
    void maximize() {
        controllerService
                .maximize()
                .enqueue(emptyCallback);
    }

    @OnClick(R.id.play_pause)
    void playOrPause() {
        controllerService
                .playOrPause()
                .enqueue(emptyCallback);
    }

    @OnClick(R.id.volume_up)
    void volumeUp() {
        controllerService
                .setVolume("up")
                .enqueue(emptyCallback);
    }

    @OnClick(R.id.volume_down)
    void volumeDown() {
        controllerService
                .setVolume("down")
                .enqueue(emptyCallback);
    }

    @OnClick(R.id.rewind)
    void rewind() {
        controllerService
                .rewind()
                .enqueue(emptyCallback);
    }

    @OnClick(R.id.fast_forward)
    void fastForward() {
        controllerService
                .fastForward()
                .enqueue(emptyCallback);
    }

    @OnTextChanged(R.id.server_ip)
    void onServerIpChanged(CharSequence charSequence) {
        hostManager.saveServerIp(charSequence.toString());
    }

    @OnClick(R.id.refresh_server_ip)
    void refreshServerIp() {
        createService();
    }

    private void setupControls() {
        final View controls = findViewById(R.id.controls);
        controls.post(new Runnable() {
            @Override
            public void run() {
                controlsAnimator = new ControlsAnimationManager(
                        controls, findViewById(R.id.show_controls));
            }
        });
    }

    private void setupHostManagement() {
        hostManager = new HostManager();
        serverIp.setText(hostManager.getServerIp());
        createService();
    }

    private Callback<String> emptyCallback = new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
        }
    };

    @Override
    public void onBackPressed() {
        String[] path = currentPath.split("/");
        if (path.length == 1) {
            super.onBackPressed();
        } else {
            undoLast();
            fetchDirectories();
        }
    }

    private void undoLast() {
        String[] path = currentPath.split("/");
        final StringBuilder pathBuffer = new StringBuilder();
        for (int i = 0; i < path.length - 1; i++) {
            pathBuffer.append(path[i]);
            if (i != path.length - 2) {
                pathBuffer.append("/");
            }
        }
        currentPath = pathBuffer.toString();
    }

    private String getCurrentDirectory() {
        String[] path = currentPath.split("/");
        return path[path.length - 1];
    }

}
