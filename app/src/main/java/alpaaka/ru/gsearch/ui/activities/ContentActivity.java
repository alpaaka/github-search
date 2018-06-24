package alpaaka.ru.gsearch.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;

import javax.inject.Inject;

import alpaaka.ru.gsearch.R;
import alpaaka.ru.gsearch.presentation.presenter.contentactivity.ContentActivityContract;
import dagger.android.support.DaggerAppCompatActivity;

public class ContentActivity extends DaggerAppCompatActivity implements ContentActivityContract.View{

    private static final int CONTAINER = R.id.container;

    @Inject
    ContentActivityContract.Presenter presenter;
    private ProgressBar progressBar;

    @Inject
    public ContentActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.takeView(this);
        setContentView(R.layout.appbar_content_actitivity);
        progressBar = findViewById(R.id.progress_bar);
        if (getIntent() != null && getIntent().getData() != null){
            presenter.loadToken(getIntent().getDataString());
        }
    }

    @Override
    protected void onStop() {
        presenter.dropView();
        super.onStop();
    }

    @Override
    public void showProgress(boolean visibility) {
        if (visibility){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showInfoMessage(int code) {
        switch (code){
            case -1:
                Snackbar.make(findViewById(CONTAINER), R.string.error_connect, Snackbar.LENGTH_SHORT)
                        .show();
                break;
            case 403:
                Snackbar.make(findViewById(CONTAINER), R.string.forbidden, Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.sign_in), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(ContentActivity.this,
                                        LoginActivity.class));
                            }
                        })
                        .show();
                break;
        }
    }
}
