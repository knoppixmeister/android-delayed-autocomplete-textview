package lv.bizapps.autocompletetextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        DelayAutoCompleteTextView actv = (DelayAutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        actv.setAdapter(new AutoCompleteAdapter(this, R.layout.list_item));
        actv.setLoadingIndicator((ProgressBar)findViewById(R.id.progress_bar));
        actv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int id1, long id2) {
				Log.e("AAA", "BBBB: "+id1);
			}
		});
    }
}

class AutoCompleteAdapter extends ArrayAdapter<String> {
	protected Context context;
	protected LayoutInflater li;

	public AutoCompleteAdapter(Context context, int resource) {
		super(context, resource);

		this.context = context;
		this.li = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@SuppressLint("ViewHolder")
	public View getView(int position, View view, ViewGroup root) {
		view = li.inflate(R.layout.list_item, root, false);

		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(getItem(position));

		return view;
	}

	@Override
	public Filter getFilter() {
		Log.e("AAA", "GET FILTER");

		Filter filter = new Filter() {
			protected void publishResults(CharSequence constraint, FilterResults results) {
				Log.e("AAA", "FILTER PUBLISH RES: "+constraint);
			}

			protected FilterResults performFiltering(CharSequence constraint) {
				Log.e("AAA", "FILTER PERFORM FILTERING: "+constraint);

				return null;
			}
		};

		return	filter;
				//super.getFilter();
	}
}