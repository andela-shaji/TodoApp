package com.example.suadahaji.todoapplication.todo;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suadahaji.todoapplication.NotesViewHolder;
import com.example.suadahaji.todoapplication.models.Note;

public interface TodoScreen {

    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     */

    interface RequiredViewOps {
        // View operations permitted to the Presenter
        Context getAppContext();
        Context getActivityContext();
        void showToast(Toast toast);
        void showProgress();
        void hideProgress();
        void showAlert(AlertDialog dialog);
        void notifyItemRemoved(int position);
        void notifyDataSetChanged();
        void notifyItemInserted(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
        void clearEditText();
    }

    /**
     * Operations offered to the View for comm with Presenter
     */
    interface ProvidedPresenterOps {
        void onDestroy(boolean isChangingConfiguration);
        void setView(RequiredViewOps view);
        NotesViewHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(NotesViewHolder holder, int position);
        int getNotesCount();
        void clickNewNote(EditText editText);
        void clickDeleteNote(Note note, int adapterPos, int layoutPos);
    }

    /**
     * Required Presenter methods available to Model.
     *      Model to Presenter
     */
    interface RequiredPresenterOps {
        Context getAppContext();
        Context getActivityContext();
    }

    /**
     * Operations offered to Model to communicate with Presenter
     */
    interface ProvidedModelOps{
        // Model Operations Permitted to the Presenter
        int getNotesCount();
        int getNote(int position);
        int insertNote(Note note);
        boolean loadData();
    }











}
