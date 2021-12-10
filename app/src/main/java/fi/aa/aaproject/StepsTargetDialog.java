package fi.aa.aaproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class StepsTargetDialog extends AppCompatDialogFragment {
    private EditText etStepsTarget;
    private StepsTargetDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Tavoiteltu askelmäärän asetus")
                .setNegativeButton("Peru", (dialog, which) -> {

                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String stepsTarget = etStepsTarget.getText().toString();
                        listener.applyStepsTargetQty(Integer.parseInt(stepsTarget));
                    }
                });
        etStepsTarget = view.findViewById(R.id.et_steps_target);

        return builder.create();
    }

    public interface StepsTargetDialogListener {
        void applyStepsTargetQty(int stepsTargetQty);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (StepsTargetDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement StepsTargetDialogListener");
        }
    }
}
