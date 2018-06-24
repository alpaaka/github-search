package alpaaka.ru.gsearch.presentation.view.choice_way;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import alpaaka.ru.gsearch.R;

public class ChoiceFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener listener;

    public static ChoiceFragment newInstance() {
        Bundle args = new Bundle();
        ChoiceFragment fragment = new ChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            this.listener = (OnFragmentInteractionListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice_way, container, false);
        Button btnAuth = view.findViewById(R.id.btn_sign_in);
        Button btnSkip = view.findViewById(R.id.btn_skip);
        btnAuth.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_sign_in:
                listener.onAuthClick();
                break;
            case R.id.btn_skip:
                listener.onSkipClick();
                break;
        }
    }

    public interface OnFragmentInteractionListener{
        void onAuthClick();
        void onSkipClick();
    }
}
