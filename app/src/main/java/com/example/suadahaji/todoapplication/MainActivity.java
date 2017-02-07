package com.example.suadahaji.todoapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.suadahaji.todoapplication.todo.TodoScreen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TodoScreen.RequiredViewOps {

    private TodoScreen.ProvidedPresenterOps mPresenter;
    private EditText mTextNewNote;
    private ListNotes mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab: {
                mPresenter.clickNewNote(mTextNewNote);
            }
        }

    }

    @Override
    public Context getAppContext() {
        return this;
    }

    @Override
    public Context getActivityContext() {
        return getApplicationContext();
    }

    @Override
    public void notifyItemInserted(int layoutPosition) {
        mListAdapter.notifyItemInserted(layoutPosition);
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        mListAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    @Override
    public void notifyDataSetChanged() {
        mListAdapter.notifyDataSetChanged();
    }


    private class ListNotes extends RecyclerView.Adapter<NotesViewHolder> {


        @Override
        public int getItemCount() {
            return mPresenter.getNotesCount();
        }

        @Override
        public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return mPresenter.createViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(NotesViewHolder holder, int position) {
            mPresenter.bindViewHolder(holder, position);
        }

    }
}
