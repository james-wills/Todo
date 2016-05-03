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
  public static final String PRIORITY_EXTRA = "todoPriority";
  public static final String POSITION_EXTRA = "todoPosition";

  ArrayList<TodoItem> items;
  ArrayAdapter<TodoItem> itemsAdapter;
  ListView lvItems;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_todo);

    lvItems = (ListView)findViewById(R.id.lvItems);

    readItems();
    itemsAdapter = new TodoAdapter(this, items);
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
      updateItem(
          i.getIntExtra(POSITION_EXTRA, -1),
          i.getStringExtra(NAME_EXTRA),
          (Priority) i.getSerializableExtra(PRIORITY_EXTRA)
      );
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
        TodoItem t = items.get(pos);
        Intent i = new Intent(TodoActivity.this, EditItemActivity.class);
        i.putExtra(NAME_EXTRA, t.getName());
        i.putExtra(PRIORITY_EXTRA, t.getPriority());
        i.putExtra(POSITION_EXTRA, pos);
        startActivityForResult(i, EDIT_REQUEST_CODE);
      }
    });
  }

  private void readItems() {
    items = new ArrayList<>(TodoItem.listAll(TodoItem.class));
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

  private void updateItem(int pos, String name, Priority priority) {
    TodoItem t = items.get(pos);
    t.setName(name);
    t.setPriority(priority);
    t.save();
    itemsAdapter.notifyDataSetChanged();
  }
}
