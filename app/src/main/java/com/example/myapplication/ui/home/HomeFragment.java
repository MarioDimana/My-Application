package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.general.Constants;

import kotlin.text.UStringsKt;

public class HomeFragment extends Fragment {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private TextView textView;
    private TextView textView5;

    char turn = 'X';
    String[][] matrix = new String[4][4];


    private void showMatrix() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }

        System.out.println();
    }


    private FragmentHomeBinding binding;

    private HomeFragment.ButtonClickListenerHome buttonClickListenerHome = new HomeFragment.ButtonClickListenerHome();

    public void switchTurn() {
        if (turn == 'X')
            turn = '0';
        else turn = 'X';
        textView.setText("it is " + turn + " turn");

    }

    private class ButtonClickListenerHome implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Button clickedButton = (Button) view;

            // Check if the button already has text set to "X" or "0"
            if (!TextUtils.isEmpty(clickedButton.getText().toString())) {
                // Button already has text, do nothing
                return;
            }

            if (view.getId() == R.id.button1) {
                // Handle click for button1
                Log.d(Constants.TAG2, "Button 1 clicked");
                matrix[1][1] = String.valueOf(turn);
                button1.setText(String.valueOf(turn));
            } else if (view.getId() == R.id.button2) {
                // Handle click for button2
                Log.d(Constants.TAG2, "Button 2 clicked");
                button2.setText(String.valueOf(turn));
                matrix[1][2] = String.valueOf(turn);
            } else if (view.getId() == R.id.button3) {
                // Handle click for button3
                Log.d(Constants.TAG2, "Button 3 clicked");
                matrix[1][3] = String.valueOf(turn);
                button3.setText(String.valueOf(turn));
            } else if (view.getId() == R.id.button4) {
                // Handle click for button4
                Log.d(Constants.TAG2, "Button 4 clicked");
                matrix[2][1] = String.valueOf(turn);
                button4.setText(String.valueOf(turn));
            } else if (view.getId() == R.id.button5) {
                // Handle click for button5
                Log.d(Constants.TAG2, "Button 5 clicked");
                matrix[2][2] = String.valueOf(turn);
                button5.setText(String.valueOf(turn));
            } else if (view.getId() == R.id.button6) {
                // Handle click for button6
                Log.d(Constants.TAG2, "Button 6 clicked");
                matrix[2][3] = String.valueOf(turn);
                button6.setText(String.valueOf(turn));
            } else if (view.getId() == R.id.button7) {
                // Handle click for button7
                Log.d(Constants.TAG2, "Button 7 clicked");
                matrix[3][1] = String.valueOf(turn);
                button7.setText(String.valueOf(turn));
            } else if (view.getId() == R.id.button8) {
                // Handle click for button8
                Log.d(Constants.TAG2, "Button 8 clicked");
                matrix[3][2] = String.valueOf(turn);
                button8.setText(String.valueOf(turn));
            } else if (view.getId() == R.id.button9) {
                // Handle click for button9
                Log.d(Constants.TAG2, "Button 9 clicked");
                matrix[3][3] = String.valueOf(turn);
                button9.setText(String.valueOf(turn));
            }
            String result = verifyWin();

            if (result != null) {

                if(result.equals("X"))
                    textView5.setText("X a castigat!");
                else
                    textView5.setText("0 a castigat!");

                
            }
            switchTurn();
            showMatrix();

            Log.d(Constants.TAG2, "ButtonClickListenerHome: button clicked");
            System.out.println("ButtonClickListenerHome: BUTTON CLICKED\n");
        }

    }

    private String verifyWin() {
        int scoreX = 0;
        int score0 = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (matrix[i][j].equals("X"))
                    scoreX++;
                else if (matrix[i][j].equals("0"))
                    score0++;
            }
            if (score0 == 3)
                return "0";
            if (scoreX == 3)
                return "X";

            score0 = 0;
            scoreX = 0;
        }


        for (int j = 1; j <= 3; j++) {
            for (int i = 1; i <= 3; i++) {
                if (matrix[i][j].equals("X"))
                    scoreX++;
                else if (matrix[i][j].equals("0"))
                    score0++;
            }
            if (score0 == 3)
                return "0";
            if (scoreX == 3)
                return "X";

            score0 = 0;
            scoreX = 0;

        }
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == j)
                    if (matrix[i][j].equals("X"))
                        scoreX++;
                    else if (matrix[i][j].equals("0"))
                        score0++;
            }
        }

        if (score0 == 3)
            return "0";
        if (scoreX == 3)
            return "X";

        score0 = 0;
        scoreX = 0;


        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i + j == 4)
                    if (matrix[i][j].equals("X"))
                        scoreX++;
                    else if (matrix[i][j].equals("0"))
                        score0++;
            }
        }

        if (score0 == 3)
            return "0";
        if (scoreX == 3)
            return "X";

        return null;

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button1 = binding.button1;
        button2 = binding.button2;
        button3 = binding.button3;
        button4 = binding.button4;
        button5 = binding.button5;
        button6 = binding.button6;
        button7 = binding.button7;
        button8 = binding.button8;
        button9 = binding.button9;

        button1.setOnClickListener(buttonClickListenerHome);
        button2.setOnClickListener(buttonClickListenerHome);
        button3.setOnClickListener(buttonClickListenerHome);
        button4.setOnClickListener(buttonClickListenerHome);
        button5.setOnClickListener(buttonClickListenerHome);
        button6.setOnClickListener(buttonClickListenerHome);
        button7.setOnClickListener(buttonClickListenerHome);
        button8.setOnClickListener(buttonClickListenerHome);
        button9.setOnClickListener(buttonClickListenerHome);

        textView = binding.textView3;
        textView5 = binding.textView5;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        textView.setText("it is "+ turn + " turn");
        textView5.setText("Joc in desfasurare..");

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = " ";
            }
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}