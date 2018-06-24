package alpaaka.ru.gsearch.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import alpaaka.ru.gsearch.BuildConfig;
import alpaaka.ru.gsearch.R;
import alpaaka.ru.gsearch.presentation.view.choice_way.ChoiceFragment;

public class LoginActivity extends AppCompatActivity
        implements ChoiceFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null & getIntent().getData() != null){

        }
        setContentView(R.layout.activity_auth);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container, ChoiceFragment.newInstance(), "ChoiceFragment")
                .commit();
    }

    @Override
    public void onAuthClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(BuildConfig.OAUTH_URL + "authorize?client_id=889dd07e08092e76f681"));
        startActivity(intent);
    }

    @Override
    public void onSkipClick() {

    }
}
