package com.example.productivity.Notes;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.productivity.R;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private List<Todo> todoList;
    private TodoAdapter.ItemClickListener clickListener;
    private TextWatcher textWatcher;
    private final int TYPE_TODO = 0;
    private final int TYPE_END = 1;

    public class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, android.text.TextWatcher {
        EditText title;
        ImageView image;

        TodoViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);

            title.addTextChangedListener(this);

            image = v.findViewById(R.id.todoImageView);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (textWatcher != null) textWatcher.beforeTextChanged(s, start, count, after);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (textWatcher != null) textWatcher.onTextChanged(s, start, before, count);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (textWatcher != null) textWatcher.afterTextChanged(s, getAdapterPosition());
        }
    }

    TodoAdapter(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        if (position == getItemCount()-1) {
            //EndViewHolder
        } else {
            holder.title.setText(todoList.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return todoList.size()+1;
    }

    void setClickListener(TodoAdapter.ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    void setTextWatcher(TextWatcher textWatcher) {
        this.textWatcher = textWatcher;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface TextWatcher {
        void afterTextChanged(Editable s, int position);
        void onTextChanged(CharSequence s, int start, int before, int count);
        void beforeTextChanged(CharSequence s, int start, int count, int after);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1) {
            return TYPE_END;
        } else {
            return TYPE_TODO;
        }
    }
}