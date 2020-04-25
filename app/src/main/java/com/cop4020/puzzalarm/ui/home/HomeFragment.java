package com.cop4020.puzzalarm.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cop4020.puzzalarm.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton selectedRadButton;

    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("PuzzlePref", Context.MODE_PRIVATE);
        String nameOfRadButton = sharedPreferences.getString("selectedPuzzle", "");

        // need to switch between depending on result of shared preferences
        if (nameOfRadButton.equals("Math Puzzle")) {
            radioButton = root.findViewById(R.id.radioButton_mathGame);
            radioButton.setChecked(true);
        }
        else if (nameOfRadButton.equals("Password Puzzle")) {
            radioButton = root.findViewById(R.id.radioButton_pwGame);
            radioButton.setChecked(true);
        }

        radioGroup = (RadioGroup) root.findViewById(R.id.radioGroup_puzzle_select);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadButton = (RadioButton) radioGroup.findViewById(checkedId);
                String selectedText = (String) selectedRadButton.getText();

                sharedPreferences = getContext().getSharedPreferences("PuzzlePref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedPuzzle", selectedText);
                editor.apply();
            }
        });

        return root;
    }
}