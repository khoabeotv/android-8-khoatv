package khoattv.retrofithw.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import khoattv.retrofithw.MainActivity;
import khoattv.retrofithw.R;
import khoattv.retrofithw.networks.Request;
import khoattv.retrofithw.networks.Response;
import khoattv.retrofithw.networks.LoginService;
import khoattv.retrofithw.networks.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

  public static final int MIN_LENGTH_INPUT = 4;
  public static final String PATTERN = "[/:*?[],.'~!@#$%^&*()+-_={}<>]\\";


  private SharedPreferences sharedPreferences;
  private SharedPreferences.Editor editor;
  private boolean remember;
  private EditText etUsername;
  private EditText etPassword;
  private Button btnSignIn;
  private TextView tvSignUp;
  private TextInputLayout ilUsername;
  private TextInputLayout ilPassword;
  private ImageView imRemember;

  public LoginFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_login, container, false);
    etUsername = (EditText) view.findViewById(R.id.et_username);
    etPassword = (EditText) view.findViewById(R.id.et_password);
    btnSignIn = (Button) view.findViewById(R.id.btn_sign_in);
    tvSignUp = (TextView) view.findViewById(R.id.tv_sign_up);
    ilPassword = (TextInputLayout) view.findViewById(R.id.il_password);
    ilUsername = (TextInputLayout) view.findViewById(R.id.il_username);
    etUsername.addTextChangedListener(new MyTextWatcher(etUsername));
    etPassword.addTextChangedListener(new MyTextWatcher(etPassword));
    imRemember = (ImageView) view.findViewById(R.id.im_remember);
    imRemember.setOnClickListener(this);
    btnSignIn.setOnClickListener(this);

    btnSignIn.setTextColor(Color.parseColor("#c5c5c5"));
    tvSignUp.setOnClickListener(this);
    remember = false;
    sharedPreferences = getActivity().getSharedPreferences("remember", Context.MODE_PRIVATE);
    editor = sharedPreferences.edit();

    String username = sharedPreferences.getString("username", "");
    String password = sharedPreferences.getString("password", "");
    if (!username.equals("") && !password.equals("")) {
      etPassword.setText(password);
      etUsername.setText(username);
    }
    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_sign_in:
        if (checkTextInput(etPassword) && checkTextInput(etUsername)) {
          LoginService loginService = RetrofitFactory.getInstance().createService(LoginService.class);
          loginService.login(new Request(etUsername.getText().toString(), etPassword.getText().toString()))
                  .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                      if (response.code() == 200) {
                        Toast.makeText(getActivity(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                        if (remember) {
                          editor.putString("username", etUsername.getText().toString().trim());
                          editor.putString("password", etPassword.getText().toString().trim());
                          editor.commit();
                        }
                      } else {
                        ilPassword.setError("Username or Password is incorrect!");
                      }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                      Toast.makeText(getActivity(), "No Internet!", Toast.LENGTH_SHORT).show();
                    }
                  });
        }
        break;
      case R.id.tv_sign_up:
        ((MainActivity)getActivity()).changeScreen(new RegisterFragment(), true);
        break;
      case R.id.im_remember:
        if (remember) {
          imRemember.setImageResource(R.drawable.ic_radio_button_unchecked_black_24dp);
          remember = false;
        } else {
          imRemember.setImageResource(R.drawable.ic_check_circle_black_24dp);
          remember = true;
        }
    }
  }

  public static boolean checkTextInput(EditText editText) {
    String s = editText.getText().toString().trim();
    if (s.length() < 4)
      return false;
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if (PATTERN.contains(String.valueOf(chars[i])))
        return false;
    }
    return true;
  }

  private class MyTextWatcher implements TextWatcher {

    private View view;

    public MyTextWatcher(View view) {
      this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
      switch (view.getId()) {
        case R.id.et_password:
          if (!checkTextInput(etPassword)) {
            ilPassword.setError("Password can not contain special characters and is longer than 3 characters!");
          } else {
            ilPassword.setErrorEnabled(false);
          }
          break;
        case R.id.et_username:
          if (!checkTextInput(etUsername)) {
            ilUsername.setError("Username can not contain special characters and is longer than 3 characters!");
          } else {
            ilUsername.setErrorEnabled(false);
          }
          break;
      }
      if (checkTextInput(etUsername) && checkTextInput(etPassword)) {
        btnSignIn.setTextColor(Color.parseColor("#3F51B5"));
      } else {
        btnSignIn.setTextColor(Color.parseColor("#c5c5c5"));
      }
    }
  }
}
