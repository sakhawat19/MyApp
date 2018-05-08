package www.fulldnnsolution.com.samedrawerallactivity_fragments.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import www.fulldnnsolution.com.samedrawerallactivity_fragments.R;

public class DialogLogin extends AppCompatDialogFragment {

    private EditText usernameEt;
    private EditText passwordEt;
    private DialogLoginListener listener;





    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);

        builder.setView(view)
                .setTitle("Sign In Here")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = usernameEt.getText().toString();
                        String password = passwordEt.getText().toString();
                        listener.applyTexts(username, password);
                    }
                });

        usernameEt = view.findViewById(R.id.username_et);
        passwordEt = view.findViewById(R.id.password_et);


        return builder.create();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogLoginListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DialogLoginListener");

        }
    }

    public interface DialogLoginListener{
        void applyTexts(String username, String password);
    }
}
