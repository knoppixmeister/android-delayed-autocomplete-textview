package lv.bizapps.autocompletetextview;

import java.util.ArrayList;
import java.util.List;

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
        actv.setThreshold(3);
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

	protected List<String> mResults = new ArrayList<String>();

	public AutoCompleteAdapter(Context context, int resource) {
		super(context, resource);

		this.context = context;
		this.li = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return mResults.size();
	}

	public String getItem(int position) {
		return mResults.get(position);
	}

	@SuppressLint("ViewHolder")
	public View getView(int position, View view, ViewGroup root) {
		view = li.inflate(R.layout.list_item, root, false);

		TextView tv = (TextView) view.findViewById(R.id.textView1);
		tv.setText(getItem(position));

		return view;
	}

	public Filter getFilter() {
		Log.e("AAA", "GET FILTER");

		Filter filter = new Filter() {
			protected FilterResults performFiltering(CharSequence constraint) {
				Log.e("AAA", "FILTER PERFORM FILTERING: "+constraint);

				FilterResults fr = new FilterResults();

				if(constraint != null && !constraint.equals("")) {
					List<String> vs = new ArrayList<String>();

					vs.add("AAA");
					vs.add("BBB");
					vs.add("CCCC");

					fr.values = vs;
					fr.count = vs.size();
				}

				return fr;
			}

			@SuppressWarnings("unchecked")
			protected void publishResults(CharSequence constraint, FilterResults results) {
				Log.e("AAA", "FILTER PUBLISH RES: "+constraint);

				if(results != null && results.count > 0) {
					mResults = (List<String>)results.values;
					notifyDataSetChanged();
				}
				else mResults = new ArrayList<String>();
						//notifyDataSetInvalidated();
			}
		};

		return	filter;
				//super.getFilter();
	}
}