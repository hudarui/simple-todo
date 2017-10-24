package com.example.hurui.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Map<String, Object>> todos = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillData();
        bindAddListener();
        render();
    }

    protected void render() {
        SimpleAdapter todoAdapter = new SimpleAdapter(
                this,
                this.todos,
                R.layout.todo_item,
                new String[]{"complete", "text"},
                new int[]{R.id.check, R.id.text});
        ListView list = (ListView)findViewById(R.id.todo_list);
        list.setAdapter(todoAdapter);
    }

    protected void bindAddListener() {
        Button addButton = (Button) findViewById(R.id.add_button);
        final EditText addText = (EditText) findViewById(R.id.add_text);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodo(addText.getText().toString());
                addText.setText("");
                render();
            }
        });
    }

    protected void fillData() {
        addTodo("hello");
        addTodo("world");
    }

    protected void addTodo(String text) {
        // Map是一个接口， HashMap是实现类
        Map<String, Object> todoItem = new HashMap<String, Object>();
        todoItem.put("complete", false);
        todoItem.put("text", text);
        this.todos.add(todoItem);
    }
}
