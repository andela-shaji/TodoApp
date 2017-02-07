package com.example.suadahaji.todoapplication.todo;


import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suadahaji.todoapplication.NotesViewHolder;
import com.example.suadahaji.todoapplication.R;
import com.example.suadahaji.todoapplication.models.Note;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TodoPresenter implements TodoScreen.ProvidedPresenterOps, TodoScreen.RequiredPresenterOps {

    private WeakReference<TodoScreen.RequiredViewOps> mView;
    private TodoScreen.ProvidedModelOps mModel;

    public TodoPresenter(TodoScreen.RequiredViewOps viewOps) {
        mView = new WeakReference<TodoScreen.RequiredViewOps>(viewOps);
    }

    // Return the view reference
    private TodoScreen.RequiredViewOps getView() throws NullPointerException {
        if (mView != null) {
            return mView.get();
        } else {
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void clickNewNote(final EditText editText) {
        getView().showProgress();
        final String noteText = editText.getText().toString();
        if ( !noteText.isEmpty() ) {
            new AsyncTask<Void, Void, Integer>() {
                @Override
                protected Integer doInBackground(Void... params) {
                    // Inserts note in Model, returning adapter position
                    return mModel.insertNote(makeNote(noteText));
                }

                @Override
                protected void onPostExecute(Integer adapterPosition) {
                    try {
                        if (adapterPosition > -1) {
                            // Note inserted
                            getView().clearEditText();
                            getView().notifyItemInserted(adapterPosition + 1);
                            getView().notifyItemRangeChanged(adapterPosition, mModel.getNotesCount());
                        } else {
                            // Informs about error
                            getView().hideProgress();
                            getView().showToast(makeToast("Error creating note [" + noteText + "]"));
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        } else {
            try {
                getView().showToast(makeToast("Cannot add a blank note!"));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }


    }

    public Note makeNote(String noteText) {

        Note note = new Note();
        note.setText( noteText );
        note.setDate(getDate());
        return note;
    }

    /**
     * Get current Date as a String
     * @return  The current date
     */
    private String getDate() {
        return new SimpleDateFormat("HH:mm:ss - MM/dd/yyyy", Locale.getDefault()).format(new Date());
    }

    private Toast makeToast(String msg) {
        return Toast.makeText(getView().getAppContext(), msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void clickDeleteNote(Note note, int adapterPos, int layoutPos) {

    }

    @Override
    public int getNotesCount() {
        return mModel.getNotesCount();
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

    @Override
    public void setView(TodoScreen.RequiredViewOps view) {

    }

    @Override
    public NotesViewHolder createViewHolder(ViewGroup parent, int viewType) {
        NotesViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View viewTaskRow = inflater.inflate(R.layout.holder_notes, parent, false);
        viewHolder = new NotesViewHolder(viewTaskRow);
        return viewHolder;
    }

    @Override
    public void bindViewHolder(final NotesViewHolder holder, int position) {
       // final Note note = mModel.getNote(position);
//        holder.text.setText(note.getText());
//        holder.date.setText(note.getDate());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  clickDeleteNote(note, holder.getAdapterPosition(), holder.getLayoutPosition());
            }
        });

    }

    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
