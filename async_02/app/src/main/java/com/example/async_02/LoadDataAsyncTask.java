package com.example.async_02;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import java.util.List;

public class LoadDataAsyncTask extends AsyncTask<Void, Void, List<AssetItem>> {
    private ListView listView;
    private Context context;

    public LoadDataAsyncTask(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected List<AssetItem> doInBackground(Void... voids) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return FileHelper.readFromFile(context);
    }

    @Override
    protected void onPostExecute(List<AssetItem> items) {
        AssetItemAdapter adapter = new AssetItemAdapter(listView.getContext(), items);
        listView.setAdapter(adapter);
    }
}
