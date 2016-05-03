package com.example.james_wills.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by james_wills on 5/2/16.
 */
public class TodoAdapter extends ArrayAdapter<TodoItem> {
  public TodoAdapter(Context context, ArrayList<TodoItem> items) {
    super(context, 0, items);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TodoItem item = getItem(position);

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
    }

    TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
    TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);

    RelativeLayout rlTodoItemRow = (RelativeLayout) convertView.findViewById(R.id.rlTodoItemRow);

    tvName.setText(item.getName());

    if (item.getPriority() == null) {
      tvPriority.setText("");
    } else {
      tvPriority.setText(item.getPriority().getName());
      rlTodoItemRow.setBackgroundColor(item.getPriority().getColor());
    }

    return convertView;
  }

}
