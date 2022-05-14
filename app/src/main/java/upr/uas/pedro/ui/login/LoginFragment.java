package upr.uas.pedro.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import upr.uas.pedro.R;
import upr.uas.pedro.db.DBHandler;
import upr.uas.pedro.db.User;

public class LoginFragment extends Fragment implements View.OnClickListener {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_login, container, false);
        Button button_submit = mView.findViewById(R.id.buttonSubmit);
        button_submit.setOnClickListener(this);
        return mView;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.buttonSubmit) {
            EditText editText_username = requireView().findViewById(R.id.editTextUsername);
            EditText editText_password = requireView().findViewById(R.id.editTextPassword);
            String username = editText_username.getText().toString();
            String password = editText_password.getText().toString();
            DBHandler db = new DBHandler(requireContext());
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            if (db.checkUser(user)) {
                TextView textNavName = Objects.requireNonNull(requireActivity()).findViewById(R.id.textNavName);
                TextView textNavUsername = Objects.requireNonNull(requireActivity()).findViewById(R.id.textNavUsername);
                textNavName.setText("Halo " + db.getName(user));
                textNavUsername.setText(db.getUsername(user));
                // Set isLogin
                user.setIsLogin(1);
                db.updateIsLogin(user);
                Snackbar.make(requireView(), "Login Success", Snackbar.LENGTH_SHORT).show();
                Log.d("LoginFragment", "User exists");
            } else {
                Snackbar.make(requireView(), "Username atau password salah", Snackbar.LENGTH_LONG).show();
                Log.d("LoginFragment", "User does not exist");
            }
        }
    }
}