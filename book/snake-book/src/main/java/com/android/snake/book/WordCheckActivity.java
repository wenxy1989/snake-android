package com.android.snake.book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.snake.model.Paragraph;
import com.android.snake.model.Sync;
import com.android.snake.model.SyncKey;
import com.android.snake.model.Word;
import com.android.snake.model.WordStatus;
import com.android.snake.utils.DateTimeUtils;

import java.util.List;

/**
 * Created by wenxy on 2017/3/15.
 */
public class WordCheckActivity extends Activity {

    protected static final String LOG_TAG = "WordCheckActivity";

    protected Context context = null;
    protected Word word = null;
    protected ListView paragraph_list = null;
    protected BaseAdapter paragraph_list_adapter = null;

    protected View.OnClickListener afterCheckPass = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setCurrentWord();
        }
    };
    protected View.OnClickListener afterCheckCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setCurrentWord();
        }
    };

    protected void afterCheckPass(View.OnClickListener afterCheckPass) {
        this.afterCheckPass = afterCheckPass;
    }

    protected void afterCheckCancel(View.OnClickListener afterCheckCancel) {
        this.afterCheckCancel = afterCheckCancel;
    }

    public int getWordCheckCurrentIndex() {
        int index = 0;
        Sync sync = Sync.getObjectByKey(SyncKey.word_checked_key.getKey());
        if (null == sync) {
            sync = new Sync();
            sync.setKey(SyncKey.word_checked_key.getKey());
            sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
            sync.setTotalCount((int) Word.count(Word.class));
            sync.setSyncCount(0);
            sync.save();
        }
        index = sync.getSyncCount() + 1;
        return index;
    }

    public void updateWordCheckedCurrentIndex() {
        Sync sync = Sync.getObjectByKey(SyncKey.word_checked_key.getKey());
        sync.setSyncCount(sync.getSyncCount() + 1);
        sync.setSyncTime(DateTimeUtils.getInstance().getNowDateTime());
        sync.save();
    }

    protected void initContext(Context context){
        this.context = context;
    }

    protected void setCurrentWord(){
        word = Word.findById(Word.class, getWordCheckCurrentIndex());
        if (null == word) {
            return;
        }
        TextView word_text = (TextView) findViewById(R.id.text_word_check_word);
        word_text.setText(word.getValue());
        paragraph_list_adapter = new ParagraphAdapter();
        paragraph_list = (ListView) findViewById(R.id.list_view);
        paragraph_list.setAdapter(paragraph_list_adapter);
        Button cancel_button = (Button) findViewById(R.id.button_word_check_cancel);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word.setStatus(WordStatus.veto.getStatus());
                word.update();
                updateWordCheckedCurrentIndex();
                if (null != afterCheckCancel) {
                    afterCheckCancel.onClick(v);
                }
            }
        });
        Button pass_button = (Button) findViewById(R.id.button_word_check_pass);
        pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                word.setStatus(WordStatus.verified.getStatus());
                word.update();
                updateWordCheckedCurrentIndex();
                if (null != afterCheckPass) {
                    afterCheckPass.onClick(v);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.word_check_layout_bottom);
        initContext(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentWord();
    }

    class ParagraphAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            int count = (int) Sync.count(Paragraph.class, "value_ like '%" + word.getValue() + "%'", null);
            return count < 20 ? count : 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(context);
            List<Paragraph> list = Paragraph.find(Paragraph.class, "value_ like '%" + word.getValue() + "%' limit ?,1", position + "");
            if (null != list && list.size() > 0) {
                Paragraph object = list.get(0);
                String html = object.getValue().replaceAll(word.getValue(), "<font color=\"#396fe2\">" + word.getValue() + "</font>");
                if (null != html && html.length() > 0) {
                    view.setText(Html.fromHtml(html, Html.FROM_HTML_OPTION_USE_CSS_COLORS));
                }else {
                    view.setText(html);
                }
            }
            return view;
        }
    }

}
