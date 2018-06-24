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
        setContentView(R.layout.activity_auth);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container, ChoiceFragment.newInstance(), "ChoiceFragment")
                .commit();
    }

    @Override
    public void onAuthClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(BuildConfig.OAUTH_URL + "authorize?client_id=" + BuildConfig.CLIENT_ID));
        startActivity(intent);
    }

    @Override
    public void onSkipClick() {
        Intent intent = new Intent(LoginActivity.this, ContentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
