package com.deuxlapins.todosqlite.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.deuxlapins.todosqlite.R;
import com.deuxlapins.todosqlite.entity.ToDoGroup;
import com.deuxlapins.todosqlite.entity.ToDo;
import com.deuxlapins.todosqlite.entity.ToDoItem;
import com.deuxlapins.todosqlite.utils.FormatterUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chetan on 06/01/17.
 */

public class ToDoListAdapter extends ArrayAdapter<Object> {

    private List<ToDo> items;
    private List<Object> allItems;

    private LayoutInflater inflater;

    public ToDoListAdapter(Context context, List<ToDo> items) {
        super(context, -1);
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        refreshDataSet();
    }

    private void refreshDataSet() {
        allItems = new ArrayList<>();

        for (ToDo toDo : items) {
            allItems.add(toDo.getTimeGroup());
            allItems.addAll(toDo.getTodoItems());
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        refreshDataSet();
    }

    @Override
    public int getViewTypeCount() {
        return 2; // two types of views - header and to do item
    }

    @Override
    public int getCount() {
        return allItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = getItem(position);

        if (item instanceof ToDoItem) {
            return 1;
        } else {
            return 0;
        }
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        Object item = allItems.get(position);
        return  item;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) == 1;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        Object item = getItem(position);

        if (item instanceof ToDoItem) {
            ToDoItem toDoItem = (ToDoItem) item;
           view = getToDoView(toDoItem, convertView, parent);
        } else {
            ToDoGroup timeGroup = (ToDoGroup) item;
            view = getHeaderView(timeGroup, convertView, parent);
        }

        return view;
    }

    @NonNull
    private View getHeaderView(ToDoGroup timeGroup, View convertView, ViewGroup parent) {
        View view;
        HeaderViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.todo_list_header_item, parent, false);
            holder = new HeaderViewHolder();
            holder.headerTitle = (TextView) view.findViewById(R.id.headerTitle);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (HeaderViewHolder) convertView.getTag();
        }

        holder.headerTitle.setText(timeGroup.getLabel());

        return view;
    }

    @NonNull
    private View getToDoView(ToDoItem item, View convertView, ViewGroup parent) {
        View view;
        ToDoViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.todo_list_item, parent, false);
            holder = new ToDoViewHolder();
            holder.title = (TextView) view.findViewById(R.id.toDoItemTitle);
            holder.date = (TextView) view.findViewById(R.id.toDoItemDate);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ToDoViewHolder) convertView.getTag();
        }

        holder.title.setText(item.getTitle());
        holder.date.setText(FormatterUtils.formatDate(item.getDueDate()));

        if(item.isDone()) {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return view;
    }

    // View Holder Pattern Classes


    private static class HeaderViewHolder {
        TextView headerTitle;
    }

    private static class ToDoViewHolder {
        TextView title;
        TextView date;
    }




}
