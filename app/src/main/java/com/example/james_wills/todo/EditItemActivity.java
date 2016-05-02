package com.example.james_wills.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class EditItemActivity extends AppCompatActivity {
  int position;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_item);

    position = getIntent().getIntExtra(TodoActivity.POSITION_EXTRA, 0);
    String name = getIntent().getStringExtra(TodoActivity.NAME_EXTRA);

    EditText etEditItem = (EditText)findViewById(R.id.etEditItem);
    etEditItem.setText(name);
  }

  public void onSaveItem(View v) {
    EditText etEditItem = (EditText)findViewById(R.id.etEditItem);
    String newName = etEditItem.getText().toString();
    Intent i = new Intent();
    i.putExtra(TodoActivity.NAME_EXTRA, newName);
    i.putExtra(TodoActivity.POSITION_EXTRA, position);
    setResult(RESULT_OK, i);
    finish();
  }
}
