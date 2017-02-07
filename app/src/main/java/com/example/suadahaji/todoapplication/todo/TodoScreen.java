package com.example.suadahaji.todoapplication.todo;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;

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

        void notifyItemInserted(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
    }

    /**
     * Operations offered to the View for comm with Presenter
     */
    interface ProvidedPresenterOps{
        // Presenter operations permitted to the View
        void clickNewNote(EditText editText);
        // setting up the recycler adapter
        int getNotesCount();
        NotesViewHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(NotesViewHolder holder, int position);


    }

    /**
     * Required Presenter operations available to Model
     */
    interface RequiredPresenterOps{
        // Presenter operations permitted to the Model
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
