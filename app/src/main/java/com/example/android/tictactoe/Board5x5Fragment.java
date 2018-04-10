package com.example.android.tictactoe;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

/**
 * TicTacToe coordinates for each square
 * -----------------------------
 * (0,0) (0,1) (0,2) (0,3) (0,4)
 * (1,0) (1,1) (1,2) (1,3) (1,4)
 * (2,0) (2,1) (2,2) (2,3) (2,4)
 * (3,0) (3,1) (3,2) (3,3) (3,4)
 * (4,0) (4,1) (4,2) (4,3) (4,4)
 * -----------------------------
 */

@SuppressWarnings("RedundantCast")
public class Board5x5Fragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    // Keys to identify the data saved
    static final String STATE_BOARD = "BOARD";
    static final String PLAYER_X_SCOREBOARD_KEY = "PLAYER_X_SCOREBOARD";
    static final String PLAYER_O_SCOREBOARD_KEY = "PLAYER_O_SCOREBOARD";
    static final String PLAYER_TO_MOVE_TEXTVIEW_KEY = "PLAYER_TO_MOVE_TEXTVIEW";
    static final String STATE_GAME_MODE = "GAME_MODE";
    static final String STATE_PLAYER_X_TURN = "PLAYER_X_TURN";
    static final String STATE_NUMBER_OF_MOVES = "NUMBER_OF_MOVES";
    static final String STATE_PLAYER_X_SCORE = "PLAYER_X_SCORE";
    static final String STATE_PLAYER_O_SCORE = "PLAYER_O_SCORE";

    static final int BOARD_SIZE = 5;
    int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private static final int EASY_MODE = 1;
    private static final int MEDIUM_MODE = 2;
    private static final int IMPOSSIBLE_MODE = 3;
    private static final int TWO_PLAYER_MODE = 4;
    int GAME_MODE = EASY_MODE;
    public boolean PLAYER_X_TURN = true;
    private static final int PLAYER_X = 1;
    private static final int PLAYER_O = 4;
    public static final int EMPTY = 0;
    Random randomNumberForBoardIndex = new Random();

    private int numberOfMoves = 0;
    private int playerXScore = 0;
    private int playerOScore = 0;

    int[] num = new int[BOARD_SIZE * BOARD_SIZE];

    private Button row0col0;
    private Button row0col1;
    private Button row0col2;
    private Button row0col3;
    private Button row0col4;

    private Button row1col0;
    private Button row1col1;
    private Button row1col2;
    private Button row1col3;
    private Button row1col4;

    private Button row2col0;
    private Button row2col1;
    private Button row2col2;
    private Button row2col3;
    private Button row2col4;

    private Button row3col0;
    private Button row3col1;
    private Button row3col2;
    private Button row3col3;
    private Button row3col4;

    private Button row4col0;
    private Button row4col1;
    private Button row4col2;
    private Button row4col3;
    private Button row4col4;

    private TextView playerXScoreboard;
    private TextView playerOScoreboard;
    private TextView playerToMoveTextView;

    private LinearLayout playerXToMoveButton;
    private LinearLayout playerOToMoveButton;

    public Board5x5Fragment() {
        // Required empty public constructor
    }

    public static Board5x5Fragment newInstance() {
        Board5x5Fragment fragment = new Board5x5Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            num = savedInstanceState.getIntArray(STATE_BOARD);
            int count = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (num != null) {
                        board[i][j] = num[count];
                        count++;
                    }
                }
            }

            PLAYER_X_TURN = savedInstanceState.getBoolean(STATE_PLAYER_X_TURN);
            numberOfMoves = savedInstanceState.getInt(STATE_NUMBER_OF_MOVES);
            playerXScore = savedInstanceState.getInt(STATE_PLAYER_X_SCORE);
            playerOScore = savedInstanceState.getInt(STATE_PLAYER_O_SCORE);
            GAME_MODE = savedInstanceState.getInt(STATE_GAME_MODE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getView() != null) {
        }
        View view = getView() != null ? getView() :
                inflater.inflate(R.layout.layout_board_5x5, container, false);
        //View view = inflater.inflate(R.layout.layout_board_5x5, container, false);

        playerXScoreboard = (TextView) view.findViewById(R.id.player_x_scoreboard);
        playerOScoreboard = (TextView) view.findViewById(R.id.player_o_scoreboard);
        playerToMoveTextView = (TextView) view.findViewById(R.id.player_to_move_textview);

        playerXToMoveButton = (LinearLayout) view.findViewById(R.id.player_x_to_move);
        playerXToMoveButton.isSelected();
        playerOToMoveButton = (LinearLayout) view.findViewById(R.id.player_o_to_move);

        row0col0 = (Button) view.findViewById(R.id.row0_col0);
        row0col1 = (Button) view.findViewById(R.id.row0_col1);
        row0col2 = (Button) view.findViewById(R.id.row0_col2);
        row0col3 = (Button) view.findViewById(R.id.row0_col3);
        row0col4 = (Button) view.findViewById(R.id.row0_col4);

        row1col0 = (Button) view.findViewById(R.id.row1_col0);
        row1col1 = (Button) view.findViewById(R.id.row1_col1);
        row1col2 = (Button) view.findViewById(R.id.row1_col2);
        row1col3 = (Button) view.findViewById(R.id.row1_col3);
        row1col4 = (Button) view.findViewById(R.id.row1_col4);

        row2col0 = (Button) view.findViewById(R.id.row2_col0);
        row2col1 = (Button) view.findViewById(R.id.row2_col1);
        row2col2 = (Button) view.findViewById(R.id.row2_col2);
        row2col3 = (Button) view.findViewById(R.id.row2_col3);
        row2col4 = (Button) view.findViewById(R.id.row2_col4);

        row3col0 = (Button) view.findViewById(R.id.row3_col0);
        row3col1 = (Button) view.findViewById(R.id.row3_col1);
        row3col2 = (Button) view.findViewById(R.id.row3_col2);
        row3col3 = (Button) view.findViewById(R.id.row3_col3);
        row3col4 = (Button) view.findViewById(R.id.row3_col4);

        row4col0 = (Button) view.findViewById(R.id.row4_col0);
        row4col1 = (Button) view.findViewById(R.id.row4_col1);
        row4col2 = (Button) view.findViewById(R.id.row4_col2);
        row4col3 = (Button) view.findViewById(R.id.row4_col3);
        row4col4 = (Button) view.findViewById(R.id.row4_col4);

        Button resetButton = (Button) view.findViewById(R.id.reset);

        row0col0.setOnClickListener(this);
        row0col1.setOnClickListener(this);
        row0col2.setOnClickListener(this);
        row0col3.setOnClickListener(this);
        row0col4.setOnClickListener(this);

        row1col0.setOnClickListener(this);
        row1col1.setOnClickListener(this);
        row1col2.setOnClickListener(this);
        row1col3.setOnClickListener(this);
        row1col4.setOnClickListener(this);

        row2col0.setOnClickListener(this);
        row2col1.setOnClickListener(this);
        row2col2.setOnClickListener(this);
        row2col3.setOnClickListener(this);
        row2col4.setOnClickListener(this);

        row3col0.setOnClickListener(this);
        row3col1.setOnClickListener(this);
        row3col2.setOnClickListener(this);
        row3col3.setOnClickListener(this);
        row3col4.setOnClickListener(this);

        row4col0.setOnClickListener(this);
        row4col1.setOnClickListener(this);
        row4col2.setOnClickListener(this);
        row4col3.setOnClickListener(this);
        row4col4.setOnClickListener(this);

        playerXToMoveButton.setOnClickListener(playerXToMoveButtonListener);
        playerOToMoveButton.setOnClickListener(playerOToMoveButtonListener);
        resetButton.setOnClickListener(resetButtonListener);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner spinner_dropdown_item
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.level_or_player_type_array, android.R.layout.simple_spinner_item);
        // Layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            num = savedInstanceState.getIntArray(STATE_BOARD);
            int count = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (num != null) {
                        board[i][j] = num[count];
                        count++;
                    }
                }
            }
            playerXScoreboard.setText(savedInstanceState.getString(PLAYER_X_SCOREBOARD_KEY));
            playerOScoreboard.setText(savedInstanceState.getString(PLAYER_O_SCOREBOARD_KEY));
            playerToMoveTextView.setText(savedInstanceState.getString(PLAYER_TO_MOVE_TEXTVIEW_KEY));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        // Save the current values
        int count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (num != null) {
                    num[count] = board[i][j];
                    count++;
                }
            }
        }
        outState.putIntArray(STATE_BOARD, num);
        outState.putCharSequence(PLAYER_X_SCOREBOARD_KEY, playerXScoreboard.getText());
        outState.putCharSequence(PLAYER_O_SCOREBOARD_KEY, playerOScoreboard.getText());
        outState.putCharSequence(PLAYER_TO_MOVE_TEXTVIEW_KEY, playerToMoveTextView.getText());
        outState.putInt(STATE_GAME_MODE, GAME_MODE);
        outState.putInt(STATE_NUMBER_OF_MOVES, numberOfMoves);
        outState.putBoolean(STATE_PLAYER_X_TURN, PLAYER_X_TURN);
        outState.putInt(STATE_PLAYER_X_SCORE, playerXScore);
        outState.putInt(STATE_PLAYER_O_SCORE, playerOScore);

        // Call to superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        playerXToMoveButton.setEnabled(false);
        playerOToMoveButton.setEnabled(false);
        switch (v.getId()) {
            case R.id.row0_col0:
                setMoveByPlayerAt(0, 0);
                break;
            case R.id.row0_col1:
                setMoveByPlayerAt(0, 1);
                break;
            case R.id.row0_col2:
                setMoveByPlayerAt(0, 2);
                break;
            case R.id.row0_col3:
                setMoveByPlayerAt(0, 3);
                break;
            case R.id.row0_col4:
                setMoveByPlayerAt(0, 4);
                break;
            case R.id.row1_col0:
                setMoveByPlayerAt(1, 0);
                break;
            case R.id.row1_col1:
                setMoveByPlayerAt(1, 1);
                break;
            case R.id.row1_col2:
                setMoveByPlayerAt(1, 2);
                break;
            case R.id.row1_col3:
                setMoveByPlayerAt(1, 3);
                break;
            case R.id.row1_col4:
                setMoveByPlayerAt(1, 4);
                break;
            case R.id.row2_col0:
                setMoveByPlayerAt(2, 0);
                break;
            case R.id.row2_col1:
                setMoveByPlayerAt(2, 1);
                break;
            case R.id.row2_col2:
                setMoveByPlayerAt(2, 2);
                break;
            case R.id.row2_col3:
                setMoveByPlayerAt(2, 3);
                break;
            case R.id.row2_col4:
                setMoveByPlayerAt(2, 4);
                break;
            case R.id.row3_col0:
                setMoveByPlayerAt(3, 0);
                break;
            case R.id.row3_col1:
                setMoveByPlayerAt(3, 1);
                break;
            case R.id.row3_col2:
                setMoveByPlayerAt(3, 2);
                break;
            case R.id.row3_col3:
                setMoveByPlayerAt(3, 3);
                break;
            case R.id.row3_col4:
                setMoveByPlayerAt(3, 4);
                break;
            case R.id.row4_col0:
                setMoveByPlayerAt(4, 0);
                break;
            case R.id.row4_col1:
                setMoveByPlayerAt(4, 1);
                break;
            case R.id.row4_col2:
                setMoveByPlayerAt(4, 2);
                break;
            case R.id.row4_col3:
                setMoveByPlayerAt(4, 3);
                break;
            case R.id.row4_col4:
                setMoveByPlayerAt(4, 4);
                break;
        }
        numberOfMoves++;
        boolean thereIsAWinner = isThereAWinner();
        if (thereIsAWinner) {
            setWinner();
            return;
        }
        if (PLAYER_X_TURN) {
            playerToMoveTextView.setText(R.string.o_move);
        } else {
            playerToMoveTextView.setText(R.string.x_move);
        }
        PLAYER_X_TURN = !PLAYER_X_TURN; // Switch the turn to next player

        if (numberOfMoves == BOARD_SIZE * BOARD_SIZE) {
            if ((isThereAWinner())) {
                playerToMoveTextView.setText(R.string.game_over);
            } else {
                playerToMoveTextView.setText(R.string.game_draw);
                DialogFragment newFragment = new gameDrawDialogFragment();
                newFragment.show(getFragmentManager(), "gameDraw");
            }
            return;
        }
        if (GAME_MODE == EASY_MODE || GAME_MODE == MEDIUM_MODE || GAME_MODE == IMPOSSIBLE_MODE) {
            if (PLAYER_X_TURN) {
                computerPlay(PLAYER_X);
            } else {
                computerPlay(PLAYER_O);
            }
        }
    }

    /* get the board value for position (i,j) */
    public int getBoardValue(int i, int j) {
        if (i < 0 || i >= BOARD_SIZE) {
            return EMPTY;
        }
        if (j < 0 || j >= BOARD_SIZE) {
            return EMPTY;
        }
        return board[i][j];
    }

    private boolean isThereAWinner() {
        int token;
        if (PLAYER_X_TURN) {
            token = 1;
        } else {
            token = 4;
        }
        final int DI[] = {-1, 0, 1, 1};
        final int DJ[] = {1, 1, 1, 0};
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {

                // Skip if the token in board[i][j] is not equal to current token
                if (board[i][j] != token) continue;
                for (int k = 0; k < 4; k++) {
                    int count = 0;
                    while (getBoardValue(i + DI[k] * count, j + DJ[k] * count) == token) {
                        count++;
                        if (count == 4) {
                            return true;
                        }
                    }
                }
            }
        return false;
    }

    private void setWinner() {
        enableAllBoxes(false);
        playerToMoveTextView.setText(R.string.game_over);
        if (PLAYER_X_TURN) {
            playerXScore++;
            playerXScoreboard.setText(String.valueOf(playerXScore));
            DialogFragment newFragment = new playerXWinsDialogFragment();
            newFragment.show(getFragmentManager(), "playerXWins");
        } else {
            playerOScore++;
            playerOScoreboard.setText(String.valueOf(playerOScore));
            DialogFragment newFragment = new playerOWinsDialogFragment();
            newFragment.show(getFragmentManager(), "playerOWins");
        }
    }

    public void setMoveByPlayerAt(int row, int column) {
        if (row == 0 && column == 0) {
            if (PLAYER_X_TURN) {
                row0col0.setText(R.string.string_x);
            } else {
                row0col0.setText(R.string.string_o);
            }
            row0col0.setEnabled(false);
        }
        if (row == 0 && column == 1) {
            if (PLAYER_X_TURN) {
                row0col1.setText(R.string.string_x);
            } else {
                row0col1.setText(R.string.string_o);
            }
            row0col1.setEnabled(false);
        }
        if (row == 0 && column == 2) {
            if (PLAYER_X_TURN) {
                row0col2.setText(R.string.string_x);
            } else {
                row0col2.setText(R.string.string_o);
            }
            row0col2.setEnabled(false);
        }
        if (row == 0 && column == 3) {
            if (PLAYER_X_TURN) {
                row0col3.setText(R.string.string_x);
            } else {
                row0col3.setText(R.string.string_o);
            }
            row0col3.setEnabled(false);
        }
        if (row == 0 && column == 4) {
            if (PLAYER_X_TURN) {
                row0col4.setText(R.string.string_x);
            } else {
                row0col4.setText(R.string.string_o);
            }
            row0col4.setEnabled(false);
        }
        if (row == 1 && column == 0) {
            if (PLAYER_X_TURN) {
                row1col0.setText(R.string.string_x);
            } else {
                row1col0.setText(R.string.string_o);
            }
            row1col0.setEnabled(false);
        }
        if (row == 1 && column == 1) {
            if (PLAYER_X_TURN) {
                row1col1.setText(R.string.string_x);
            } else {
                row1col1.setText(R.string.string_o);
            }
            row1col1.setEnabled(false);
        }
        if (row == 1 && column == 2) {
            if (PLAYER_X_TURN) {
                row1col2.setText(R.string.string_x);
            } else {
                row1col2.setText(R.string.string_o);
            }
            row1col2.setEnabled(false);
        }
        if (row == 1 && column == 3) {
            if (PLAYER_X_TURN) {
                row1col3.setText(R.string.string_x);
            } else {
                row1col3.setText(R.string.string_o);
            }
            row1col3.setEnabled(false);
        }
        if (row == 1 && column == 4) {
            if (PLAYER_X_TURN) {
                row1col4.setText(R.string.string_x);
            } else {
                row1col4.setText(R.string.string_o);
            }
            row1col4.setEnabled(false);
        }
        if (row == 2 && column == 0) {
            if (PLAYER_X_TURN) {
                row2col0.setText(R.string.string_x);
            } else {
                row2col0.setText(R.string.string_o);
            }
            row2col0.setEnabled(false);
        }
        if (row == 2 && column == 1) {
            if (PLAYER_X_TURN) {
                row2col1.setText(R.string.string_x);
            } else {
                row2col1.setText(R.string.string_o);
            }
            row2col1.setEnabled(false);
        }
        if (row == 2 && column == 2) {
            if (PLAYER_X_TURN) {
                row2col2.setText(R.string.string_x);
            } else {
                row2col2.setText(R.string.string_o);
            }
            row2col2.setEnabled(false);
        }
        if (row == 2 && column == 3) {
            if (PLAYER_X_TURN) {
                row2col3.setText(R.string.string_x);
            } else {
                row2col3.setText(R.string.string_o);
            }
            row2col3.setEnabled(false);
        }
        if (row == 2 && column == 4) {
            if (PLAYER_X_TURN) {
                row2col4.setText(R.string.string_x);
            } else {
                row2col4.setText(R.string.string_o);
            }
            row2col4.setEnabled(false);
        }
        if (row == 3 && column == 0) {
            if (PLAYER_X_TURN) {
                row3col0.setText(R.string.string_x);
            } else {
                row3col0.setText(R.string.string_o);
            }
            row3col0.setEnabled(false);
        }
        if (row == 3 && column == 1) {
            if (PLAYER_X_TURN) {
                row3col1.setText(R.string.string_x);
            } else {
                row3col1.setText(R.string.string_o);
            }
            row3col1.setEnabled(false);
        }
        if (row == 3 && column == 2) {
            if (PLAYER_X_TURN) {
                row3col2.setText(R.string.string_x);
            } else {
                row3col2.setText(R.string.string_o);
            }
            row3col2.setEnabled(false);
        }
        if (row == 3 && column == 3) {
            if (PLAYER_X_TURN) {
                row3col3.setText(R.string.string_x);
            } else {
                row3col3.setText(R.string.string_o);
            }
            row3col3.setEnabled(false);
        }
        if (row == 3 && column == 4) {
            if (PLAYER_X_TURN) {
                row3col4.setText(R.string.string_x);
            } else {
                row3col4.setText(R.string.string_o);
            }
            row3col4.setEnabled(false);
        }
        if (row == 4 && column == 0) {
            if (PLAYER_X_TURN) {
                row4col0.setText(R.string.string_x);
            } else {
                row4col0.setText(R.string.string_o);
            }
            row4col0.setEnabled(false);
        }
        if (row == 4 && column == 1) {
            if (PLAYER_X_TURN) {
                row4col1.setText(R.string.string_x);
            } else {
                row4col1.setText(R.string.string_o);
            }
            row4col1.setEnabled(false);
        }
        if (row == 4 && column == 2) {
            if (PLAYER_X_TURN) {
                row4col2.setText(R.string.string_x);
            } else {
                row4col2.setText(R.string.string_o);
            }
            row4col2.setEnabled(false);
        }
        if (row == 4 && column == 3) {
            if (PLAYER_X_TURN) {
                row4col3.setText(R.string.string_x);
            } else {
                row4col3.setText(R.string.string_o);
            }
            row4col3.setEnabled(false);
        }
        if (row == 4 && column == 4) {
            if (PLAYER_X_TURN) {
                row4col4.setText(R.string.string_x);
            } else {
                row4col4.setText(R.string.string_o);
            }
            row4col4.setEnabled(false);
        }
        if (PLAYER_X_TURN) {
            board[row][column] = 1;
        } else {
            board[row][column] = 4;
        }
    }

    private void computerPlay(int playerWithTurnNumber) {
        if (GAME_MODE == EASY_MODE) {
            playRandom();
        } else if (GAME_MODE == MEDIUM_MODE || GAME_MODE == IMPOSSIBLE_MODE) {
            playMediumOrImpossibleMode(playerWithTurnNumber);
        }
        numberOfMoves++;
        boolean thereIsAWinner = isThereAWinner();
        if (thereIsAWinner) {
            setWinner();
            return;
        }
        if (numberOfMoves == BOARD_SIZE * BOARD_SIZE && !(isThereAWinner())) {
            playerToMoveTextView.setText(R.string.game_draw);
            DialogFragment newFragment = new gameDrawDialogFragment();
            newFragment.show(getFragmentManager(), "gameDraw");
            return;
        }
        if (PLAYER_X_TURN) {
            playerToMoveTextView.setText(R.string.o_move);
        } else {
            playerToMoveTextView.setText(R.string.x_move);
        }
        PLAYER_X_TURN = !PLAYER_X_TURN;
    }

    private void playRandom() {
        int iIndex = 0;
        int jIndex = 1;
        while (!(play(iIndex, jIndex))) {
            // Keep trying until a successful move is played
            iIndex = randomNumberForBoardIndex.nextInt(BOARD_SIZE);
            jIndex = randomNumberForBoardIndex.nextInt(BOARD_SIZE);
        }
    }

    private void playMediumOrImpossibleMode(int playerWithTurnNumber) {
        boolean carry = true; // Is used so that only one module is executed.
        if (GAME_MODE == IMPOSSIBLE_MODE) {
            carry = winOrBlockMove(playerWithTurnNumber); // Checking for 2/3 win situation.
            if (!carry) {
                enableAllBoxes(false);
                playerToMoveTextView.setText(R.string.game_over);
                return;
            }
        }
        if ((GAME_MODE == MEDIUM_MODE || GAME_MODE == IMPOSSIBLE_MODE) && carry) {
            if (numberOfMoves == 0) {
                playRandom();
                return;
            } else if (numberOfMoves == 1) {
                if (!(play(1, 1))) {
                    // If the square at the center is already played, play any of the corner squares
                    int i = 0;
                    int j = 2;
                    int c = randomNumberForBoardIndex.nextBoolean() ? i : j;
                    int d = randomNumberForBoardIndex.nextBoolean() ? i : j;
                    setMoveByPlayerAt(c, d);
                } else {
                    setMoveByPlayerAt(1, 1);
                }
                return;
            } else if (numberOfMoves >= 1) {
                // playerWithTurnNumber: 1 for X and 4 for O
                if (PLAYER_X_TURN) {
                    carry = winOrBlockMove(4); // Checking for situation where loss may occur.
                } else {
                    carry = winOrBlockMove(1);
                }
            }
        }
        if (carry) {
            playRandom();
        }
    }

    boolean winOrBlockMove(int playerWithTurnNumber) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                // Checking corresponding row for 2/3 situation.
                if (board[i][0] + board[i][1] + board[i][2] + board[i][3] + board[i][4] == playerWithTurnNumber * 3) {
                    if (play(i, j)) {   // Play the move.
                        return false;
                    }
                }
                // Checking corresponding column for 2/3 situation.
                else if (board[0][j] + board[1][j] + board[2][j] + board[3][j] + board[4][j] == playerWithTurnNumber * 3) {
                    if (play(i, j)) {
                        return false;
                    }
                }
            }
        }
        // Checking first diagonal for 2/3.
        if (board[0][0] + board[1][1] + board[2][2] + board[3][3] + board[4][4] == playerWithTurnNumber * 3) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (play(i, i)) {
                    return false;
                }
            }
        }
        // Checking second diagonal for 2/3.
        else if (board[4][0] + board[3][1] + board[2][2] + board[1][3] + board[0][4] == playerWithTurnNumber * 3) {
            for (int i = 0, j = 2; i < BOARD_SIZE; i++, j--) {
                if (play(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean play(int row, int column) {
        // If square hasn't been played yet
        if (board[row][column] == 0) {
            setMoveByPlayerAt(row, column);
            return true;
        } else
            return false;
    }

    /*
    * Method to handle spinner selection
    */
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 0:
                // Easy is clicked
                GAME_MODE = EASY_MODE;
                initGame(GAME_MODE);
                resetScoreBoard();
                break;
            case 1:
                // Medium is clicked
                GAME_MODE = MEDIUM_MODE;
                initGame(GAME_MODE);
                resetScoreBoard();
                break;
            case 2:
                // Impossible is clicked
                GAME_MODE = IMPOSSIBLE_MODE;
                initGame(GAME_MODE);
                resetScoreBoard();
                break;
            case 3:
                // Two Players is clicked
                GAME_MODE = TWO_PLAYER_MODE;
                initGame(GAME_MODE);
                resetScoreBoard();
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private void enableAllBoxes(boolean value) {
        row0col0.setEnabled(value);
        row0col1.setEnabled(value);
        row0col2.setEnabled(value);
        row0col3.setEnabled(value);
        row0col4.setEnabled(value);

        row1col0.setEnabled(value);
        row1col1.setEnabled(value);
        row1col2.setEnabled(value);
        row1col3.setEnabled(value);
        row1col4.setEnabled(value);

        row2col0.setEnabled(value);
        row2col1.setEnabled(value);
        row2col2.setEnabled(value);
        row2col3.setEnabled(value);
        row2col4.setEnabled(value);

        row3col0.setEnabled(value);
        row3col1.setEnabled(value);
        row3col2.setEnabled(value);
        row3col3.setEnabled(value);
        row3col4.setEnabled(value);

        row4col0.setEnabled(value);
        row4col1.setEnabled(value);
        row4col2.setEnabled(value);
        row4col3.setEnabled(value);
        row4col4.setEnabled(value);
    }

    private void resetScoreBoard() {
        playerXScore = 0;
        playerOScore = 0;
        playerXScoreboard.setText("-");
        playerOScoreboard.setText("-");
    }

    private View.OnClickListener resetButtonListener = new View.OnClickListener() {
        public void onClick(View reset) {
            initGame(GAME_MODE);
        }
    };

    private void initGame(int gameMode) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                // Fill the board with zeros(0)
                board[row][column] = 0;
            }
        }
        PLAYER_X_TURN = true;
        playerXToMoveButton.isSelected();
        playerToMoveTextView.setText(R.string.notice_board);
        playerXToMoveButton.setEnabled(true);
        playerOToMoveButton.setEnabled(true);
        GAME_MODE = gameMode;
        resetBoard();
    }

    private void resetBoard() {
        row0col0.setText("");
        row0col1.setText("");
        row0col2.setText("");
        row0col3.setText("");
        row0col4.setText("");

        row1col0.setText("");
        row1col1.setText("");
        row1col2.setText("");
        row1col3.setText("");
        row1col4.setText("");

        row2col0.setText("");
        row2col1.setText("");
        row2col2.setText("");
        row2col3.setText("");
        row2col4.setText("");

        row3col0.setText("");
        row3col1.setText("");
        row3col2.setText("");
        row3col3.setText("");
        row3col4.setText("");

        row4col0.setText("");
        row4col1.setText("");
        row4col2.setText("");
        row4col3.setText("");
        row4col4.setText("");

        enableAllBoxes(true);
        numberOfMoves = 0;
    }

    private View.OnClickListener playerXToMoveButtonListener = new View.OnClickListener() {
        public void onClick(View player_x_to_move) {
            playerXToMoveButton.setEnabled(false);
            playerOToMoveButton.setEnabled(false);
            PLAYER_X_TURN = true;
            playerToMoveTextView.setText(R.string.x_move);
            if (GAME_MODE == EASY_MODE
                    || GAME_MODE == MEDIUM_MODE
                    || GAME_MODE == IMPOSSIBLE_MODE) {
                computerPlay(PLAYER_X);
            }
        }
    };

    private View.OnClickListener playerOToMoveButtonListener = new View.OnClickListener() {
        public void onClick(View player_o_to_move) {
            playerXToMoveButton.setEnabled(false);
            playerOToMoveButton.setEnabled(false);
            PLAYER_X_TURN = false;
            playerToMoveTextView.setText(R.string.o_move);
            if (GAME_MODE == EASY_MODE
                    || GAME_MODE == MEDIUM_MODE
                    || GAME_MODE == IMPOSSIBLE_MODE) {
                computerPlay(PLAYER_O);
            }
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        //Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        //Log.d(TAG, "onDetach: ");
        super.onDetach();
    }
}