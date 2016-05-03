package com.example.james_wills.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
  private final int EDIT_REQUEST_CODE = 1;

  public static final String NAME_EXTRA = "todoName";
  public static final String POSITION_EXTRA = "todoID";

  ArrayList<TodoItem> items;
  ArrayAdapter<TodoItem> itemsAdapter;
  ListView lvItems;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo);

    lvItems = (ListView)findViewById(R.id.lvItems);

    readItems();
    itemsAdapter = new ArrayAdapter<TodoItem>(this,
            android.R.layout.simple_list_item_1, items);
    lvItems.setAdapter(itemsAdapter);

    setupListViewListener();
  }

  public void onAddItem(View v) {
    EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
    addItem(etNewItem.getText().toString());
    etNewItem.setText("");
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent i) {
    if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {
      updateItem(i.getIntExtra(POSITION_EXTRA, -1), i.getStringExtra(NAME_EXTRA));
    }
  }

  private void setupListViewListener() {
    lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
      @Override
      public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
        removeItem(pos);
        return true;
      }
    });

    lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
        TodoItem todoItem = items.get(pos);
        Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
        i.putExtra(NAME_EXTRA, items.get(pos).toString());
        i.putExtra(POSITION_EXTRA, pos);
        startActivityForResult(i, EDIT_REQUEST_CODE);
      }
    });
  }

  private void readItems() {
    items = new ArrayList<TodoItem>(TodoItem.listAll(TodoItem.class));
  }

  private void removeItem(int position) {
    TodoItem t = items.get(position);
    t.delete();
    items.remove(position);
    itemsAdapter.notifyDataSetChanged();
  }

  private void addItem(String name) {
    TodoItem t = new TodoItem(name);
    t.save();
    itemsAdapter.add(t);
  }

  private void updateItem(int pos, String name) {
    TodoItem t = items.get(pos);
    t.setName(name);
    t.save();
    itemsAdapter.notifyDataSetChanged();
  }
}
