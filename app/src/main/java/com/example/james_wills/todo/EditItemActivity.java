package com.example.james_wills.todo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditItemActivity extends AppCompatActivity {

  private int position;
  private Priority priority;

  private EditText etEditItem;
  private TextView tvPriority;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_item);

    position = getIntent().getIntExtra(TodoActivity.POSITION_EXTRA, -1);

    tvPriority = (TextView)findViewById(R.id.tvPriority);
    priority = (Priority) getIntent().getSerializableExtra(TodoActivity.PRIORITY_EXTRA);
    tvPriority.setText(priority.toString());

    String name = getIntent().getStringExtra(TodoActivity.NAME_EXTRA);
    etEditItem = (EditText)findViewById(R.id.etEditItem);
    etEditItem.setText(name);

    setTitle("Edit Item");
    setListeners();
  }

  private void setListeners() {
    tvPriority.setOnClickListener(new TextView.OnClickListener() {
      @Override
      public void onClick(View item) {
        new AlertDialog.Builder(EditItemActivity.this)
            .setSingleChoiceItems(Priority.getPriorityNames(), priority.getValue(), null)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
                int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                priority = Priority.getPriorities()[selectedPosition];
                tvPriority.setText(priority.getName());
              }
            })
            .show();
      }
    });
  }

  public void onSaveItem(View v) {
    String newName = etEditItem.getText().toString();

    Intent i = new Intent();
    i.putExtra(TodoActivity.NAME_EXTRA, newName);
    i.putExtra(TodoActivity.POSITION_EXTRA, position);
    i.putExtra(TodoActivity.PRIORITY_EXTRA, priority);
    setResult(RESULT_OK, i);
    finish();
  }
}
